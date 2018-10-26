package com.innothink.innometer.security.oauth.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.innothink.innometer.common.domain.Users;
import com.innothink.innometer.common.repository.ClientRepository;
import com.innothink.innometer.common.repository.CustomerRepository;
import com.innothink.innometer.common.repository.RoleAuthorityRepository;
import com.innothink.innometer.common.repository.UserAuthorityRepository;
import com.innothink.innometer.common.repository.UserRoleRepository;
import com.innothink.innometer.common.repository.UsersRepository;
import com.innothink.innometer.common.service.ActivityLogService;
import com.innothink.innometer.constants.ApplicationConstants;
import com.innothink.innometer.exception.ApplicationException;
import com.innothink.innometer.security.AuthoritiesConstants;
import com.innothink.innometer.util.CommonUtil;
import com.innothink.innometer.web.rest.errors.ErrorConstants;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;

@Service("userAuthenticationService")
public class UserAuthenticationService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private UsersRepository usersRepository;
	private UserAuthorityRepository userAuthorityRepository;
	private RoleAuthorityRepository roleAuthorityRepository;
	private UserRoleRepository userRoleRepository;
	private ActivityLogService activityLogService;
	private ClientRepository clientRepository;
	private CustomerRepository customerRepository;

	@Inject
	public UserAuthenticationService(RoleAuthorityRepository roleAuthorityRepository, UsersRepository usersRepository,
			UserAuthorityRepository userAuthorityRepository, UserRoleRepository userRoleRepository,
			ActivityLogService activityLogService, ClientRepository clientRepository,
			CustomerRepository customerRepository) {
		this.usersRepository = usersRepository;
		this.userAuthorityRepository = userAuthorityRepository;
		this.roleAuthorityRepository = roleAuthorityRepository;
		this.userRoleRepository = userRoleRepository;
		this.activityLogService = activityLogService;
		this.clientRepository = clientRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		logger.info("Performing authentication for the user: {}", userName);
		checkLicence();
		/**
		 * Getting User Information by userName After we received we are sending
		 * this Spring authManage to authenticate
		 */
		if(userName.equals(AuthoritiesConstants.IOT_EMAIL_ID)){
			Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority("PROFILE_VIEW")));
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return new org.springframework.security.core.userdetails.User(userName, passwordEncoder.encode(AuthoritiesConstants.IOT_EMAIL_PASSWORD),
					grantedAuthorities);
		}
		Users userInformation = usersRepository.findByUserEmailId(userName);
		if (userInformation == null) {
			logger.error("Bad Credential : {}", userName);
			throw new ApplicationException(ErrorConstants.USER_NOT_REGISTER);
		}
		String clientName = clientRepository.getClientNameByClientId(userInformation.getClientId());
		String customerName = customerRepository.getCustomerNameByCustomerId(userInformation.getCustomerId());
		String comment = userInformation.getEmailId() + " This user Login successfully";
		String refType = "UserLogin";
		String firstName="";
		String lastName="";
		String emailId="";
		if (Objects.nonNull(userInformation.getUserId())){
			
			if (null !=usersRepository) {
				Users users = usersRepository.findOne(userInformation.getUserId());
				
				firstName=users.getFirstName();
				lastName=users.getLastName();
				emailId=users.getEmailId();
			}	
		}
			
		activityLogService.saveActivityLog(userInformation.getUserId(), userInformation.getUserId(), comment, refType,
				null, null, null, null, clientName, customerName, userInformation.getClientId(),
				userInformation.getCustomerId(), firstName, lastName, emailId, ApplicationConstants.GET.toUpperCase());
		/**
		 * GrantedAuthority Getting user roles information and sending this to
		 * Spring authManager to authorize
		 */
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		populateGrantedAuthority(userInformation, grantedAuthorities);

		return new org.springframework.security.core.userdetails.User(userName, userInformation.getPassword(),
				grantedAuthorities);
	}
	
	@SuppressWarnings({ "resource", "unchecked" })
	private void checkLicence() {
		String fileName = null;
		String line = null;
		
		if (StringUtils.isBlank(System.getProperty("licence.path"))) {
			// fileName = System.getProperty("licence.path");
			fileName = "C:/Users/smi-user/Desktop/Certificate/innometer.cert";

			try {
				File f = new File(fileName);
				if (!f.exists() && !f.isDirectory()) {
					throw new ApplicationException("Licence not installed Please contact customer support");
				} else {
					FileReader fileReader = new FileReader(fileName);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					StringBuffer buf = new StringBuffer();
					while ((line = bufferedReader.readLine()) != null) {
						buf.append(line);
					}

					byte[] valueDecoded = Base64.decodeBase64(buf.toString());
					String decryptedStr = CommonUtil.decrypt(new String(valueDecoded));

					byte[] original = decryptedStr.getBytes();
					ObjectMapper objectMapper = new ObjectMapper();
					Map<String, String> myMap = new HashMap<String, String>();
					myMap = objectMapper.readValue(original, HashMap.class);

					String currentDate = sdf.format(new Date());

					if (CommonUtil.checkExpireDate(currentDate, myMap.get("expiryDate"))) {
						throw new ApplicationException(
								"Please contact customer support because your Subscription has Expired on "
										+ myMap.get("expiryDate"));
					}

					if (!CommonUtil.macAddressValidate(myMap.get("macAddress"), "08-62-66-2C-6B-4C")) {
						throw new ApplicationException(
								"Please contact customer support because your MAC address is different");
					}

					if (myMap.get("activated").equalsIgnoreCase("true")) {
						throw new ApplicationException(
								"Please contact customer support because this licence used some other system");
					}

					//checkDayCount(myMap);
					bufferedReader.close();
				}

			} catch (FileNotFoundException ex) {
				throw new ApplicationException("Certificate file not available. Please contact customer support");
			} catch (IOException ex) {
				throw new ApplicationException("Error reading file '" + fileName + "'");
			}
		} else {
			throw new ApplicationException("Licence configuration not done");
		}

	}

	public byte[] dayCountWrite(String day, String fileName) throws IOException {
		byte[] bytesEncoded = Base64.encodeBase64(day.getBytes());
		try (FileOutputStream fos = new FileOutputStream(fileName)) {
			fos.write(bytesEncoded);
			if (fos != null)
				fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return bytesEncoded;

	}

	public String fileReader(String inputFileName) throws IOException {
		FileReader fileReader;
		String line = null;
		StringBuffer buf = new StringBuffer();
		try {
			fileReader = new FileReader(inputFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				buf.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return buf.toString();

	}

	public boolean checkDayCount(Map<String, String> myMap) {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 7);
		today.set(Calendar.MINUTE, 45);
		today.set(Calendar.SECOND, 0);

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				String fileName = null;
				if (StringUtils.isBlank(System.getProperty("licence.key.path"))) {
					// fileName = System.getProperty("licence.path");
					fileName = "C://Users/smi-user/Desktop/Certificate/innometer.key";
					int count = 0;
					try {
						File f = new File(fileName);
						if (!f.exists() && !f.isDirectory()) {
							count = Integer.valueOf(myMap.get("dailyCount"));
							myMap.put("dailyCount", String.valueOf(count));

						} else {
							String countData = fileReader(fileName);
							byte[] countDecoded = Base64.decodeBase64(countData);
							String countStr = new String(countDecoded);
							count = Integer.valueOf(countStr);
						}
						count++;
						dayCountWrite(String.valueOf(count), fileName);

						int validatityInDays = Integer.valueOf(myMap.get("validatityInDays"));
						if (validatityInDays < count) {
							timer.cancel();
							timer.purge();
							throw new ApplicationException(
									"Please contact customer support because your Subscription has Expired");
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					throw new ApplicationException("Licence key configuration not done");
				}

			}
		};
		// timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1,
		// TimeUnit.DAYS)); // period: 1 day
		timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS)); // period: 5 seconds
		return false;

	}

	private void populateGrantedAuthority(Users user, Collection<GrantedAuthority> grantedAuthorities) {
		GrantedAuthority grantedAuthority;
		
		List<UUID> userRoleIds = userRoleRepository.findRoleIdsByUserId(user.getUserId());
		if(CollectionUtils.isEmpty(userRoleIds)){
			throw new ApplicationException(ErrorConstants.ROLE_NOT_ASSIGN);
		}		
		List<String> userRoleIdstr = new ArrayList<>();
		userRoleIds.forEach(roleId -> userRoleIdstr.add(roleId.toString()));
		
		List<?> authorizedProcessList = userAuthorityRepository.getAllProcessByUserId(user.getUserId().toString());


		if (CollectionUtils.isEmpty(authorizedProcessList)) {
			authorizedProcessList = roleAuthorityRepository.getAllProcessByRoleId(userRoleIdstr);
		}
		if (CollectionUtils.isNotEmpty(authorizedProcessList)) {
			for (Object roleAuthorityView : authorizedProcessList) {

				if (roleAuthorityView instanceof Object[]) {
					Object[] objects = (Object[]) roleAuthorityView;

					List<String> actionsList = new ArrayList<>(
							Arrays.asList(objects[1].toString().split(ApplicationConstants.COMMA)));
					for (String action : actionsList) {
						grantedAuthority = new SimpleGrantedAuthority(action);
						grantedAuthorities.add(grantedAuthority);
					}
				}

			}
			grantedAuthority = new SimpleGrantedAuthority("PROFILE_VIEW");
			grantedAuthorities.add(grantedAuthority);
			
			logger.debug(" authorizedProcessList :{}", authorizedProcessList);
		} else {
			grantedAuthority = new SimpleGrantedAuthority(ApplicationConstants.DEFAULT_ROLE);
			grantedAuthorities.add(grantedAuthority);
		}
	}

}