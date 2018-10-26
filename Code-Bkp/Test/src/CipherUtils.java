import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class CipherUtils {

	String secretkey = "techmangolocal12";
	private byte[] key = secretkey.getBytes(StandardCharsets.UTF_8);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public String encrypt(String strToEncrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
			return encryptedString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
			return decryptedString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getMAC() {
		SystemInfo systemInfo = new SystemInfo();
		String mac = null;
		try {
			String OSName = System.getProperty("os.name");
			if (OSName.contains("Windows")) {
				mac = systemInfo.getWindowsMAC();
			} else {
				mac = systemInfo.getMAC4Linux("eth0");
				if (mac == null) {
					mac = systemInfo.getMAC4Linux("eth1");
					if (mac == null) {
						mac = systemInfo.getMAC4Linux("eth2");
						if (mac == null) {
							mac = systemInfo.getMAC4Linux("usb0");
						}
					}
				}
			}
		} catch (Exception E) {
			System.err.println("System Mac Exp : " + E.getMessage());
		}
		return mac;
	}
	
	public boolean macAddressValidate(String existMacAddress,String macAddress) {
		if(StringUtils.isNotBlank(existMacAddress) && StringUtils.isNotBlank(macAddress)) {
			System.out.println("existMacAddress>>>>"+existMacAddress);
			System.out.println("macAddress"+macAddress);
			if(existMacAddress.equalsIgnoreCase(macAddress)) {
				return true;
			}
				
		}
		return false;
		
	}
	
	public boolean checkExpireDate(String currentDate, String expireDate) {
		Date date1;
		try {
			
			date1 = sdf.parse(currentDate);
			Date date2 = sdf.parse(expireDate);
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(date1);
			cal2.setTime(date2);

			if (cal1.after(cal2)) {
				System.out.println("Date is greater so need to renewal");
				return true;
			}

			/*if (cal1.before(cal2)) {
				System.out.println("Date1 is before Date2");
				return true;
			}*/

			/*if (cal1.equals(cal2)) {
				System.out.println("Date1 is equal Date2");
			}*/
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public byte[] dayCountWrite(String day,String fileName) throws IOException {
		// Encode data on your side using BASE64
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
				String fileName = "C://Users/smi-user/Desktop/Certificate/innometer.key";
				int count = 0;
				try {

					File f = new File(fileName);
					if (!f.exists() && !f.isDirectory()) {
						System.out.println("File not exist");
						count = Integer.valueOf(myMap.get("dailyCount"));
						myMap.put("dailyCount", String.valueOf(count));

					} else {
						String countData = fileReader(fileName);
						//System.out.println("countData>>>" + countData);
						byte[] countDecoded = Base64.decodeBase64(countData);
						//System.out.println("countDecoded>>>>" + new String(countDecoded));
						String countStr = new String(countDecoded);
						count = Integer.valueOf(countStr);
						System.out.println("count>>>" + count);
					}
					count++;
					dayCountWrite(String.valueOf(count), fileName);
					System.out.println("\n");
					//System.out.println("myMap>>>>>" + myMap);

					// Decode data on other side, by processing encoded data
					int validatityInDays = Integer.valueOf(myMap.get("validatityInDays"));
					if (validatityInDays < count) {
						System.out.println("Please contact customer support because your Subscription has Expired");
						System.out.println("\n");
						timer.cancel();
						timer.purge();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
		// timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1,
		// TimeUnit.DAYS)); // period: 1 day
		timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS)); // period: 5 seconds
		return false;

	}

	public static void main(String args[]) {

		String fileName = "C://Users/smi-user/Desktop/Certificate/innometer.cert";
		String line = null;
		String activationDate=null;
		String expireDate=null;
		CipherUtils cipher = new CipherUtils();
		SystemInfo systemInfo = new SystemInfo();

		try {
			
			File f = new File(fileName);
			if (!f.exists() && !f.isDirectory()) {
				System.out.println("Certificate/Licence is not available please contact customer support/renewal your subscription");
			}else {
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer buf = new StringBuffer();
				while ((line = bufferedReader.readLine()) != null) {
					buf.append(line);
				}

				
				byte[] valueDecoded = Base64.decodeBase64(buf.toString());
				//System.out.println("Base64 valueDecoded>>>>"+new String(valueDecoded));
				
				//String encryptedStr = cipher.encrypt(buf.toString());
				String decryptedStr = cipher.decrypt(new String(valueDecoded));

				//System.out.println("string after encription ::   " + encryptedStr);
				//System.out.println("Base 64 after AES decription ::  " + decryptedStr);

				byte[] original = decryptedStr.getBytes();
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, String> myMap = new HashMap<String, String>();
				myMap = objectMapper.readValue(original, HashMap.class);

				String currentDate = sdf.format(new Date());
				
				if (StringUtils.isBlank(myMap.get("activationDate"))) {
					myMap.put("activationDate", currentDate);

				}
				if (StringUtils.isBlank(myMap.get("expiryDate"))) {
					expireDate=activationDate=myMap.get("expiryDate");
				}
				
				
				if (StringUtils.isBlank(myMap.get("ipAddress"))|| StringUtils.isBlank(myMap.get("macAddress")) || StringUtils.isBlank(myMap.get("hostName"))) {
					myMap.put("hostName", systemInfo.getSystemName());
					myMap.put("ipAddress", systemInfo.getIPAddress());
					myMap.put("macAddress", cipher.getMAC());
				}
				
				/*if (StringUtils.isBlank(myMap.get("activated"))) {
					myMap.put("activated", "true");

				}
				*/
				
				/*activationDate=myMap.get("ActivationDate");
				expireDate=activationDate=myMap.get("expiryDate");
				
				System.out.println("activationDate >>>>"+myMap.get("ActivationDate"));
				System.out.println("expireDate >>>>>"+myMap.get("expiryDate"));*/
				
				
				//if(cipher.checkExpireDate(myMap.get("ActivationDate"),activationDate)) {
				if(cipher.checkExpireDate(currentDate, myMap.get("expiryDate"))) {
					System.out.println("Please contact customer support because your Subscription has Expired");
					System.out.println("\n");
				}
				
				if(!cipher.macAddressValidate(myMap.get("macAddress"),"08-62-66-2C-6B-4C1")) {
					System.out.println("Please contact customer support because your MAC address is different");
					System.out.println("\n");
				}
				boolean activated=Boolean.valueOf(myMap.get("activated"));
				if(activated) {
					System.out.println("Please contact customer support because this licence used some other system");
					System.out.println("\n");
				}
				
				cipher.checkDayCount(myMap);
				
				//System.out.println("myMap ::::::::::"+myMap);
				bufferedReader.close();
			}
			
		} catch (FileNotFoundException ex) {
			System.out.println("Certificate file Not available please contact customer support");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			ex.printStackTrace();
		}
	}
}
