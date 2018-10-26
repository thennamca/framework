import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonCreation {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) throws IOException {
		Map<String,String> myMap = new HashMap<String,String>();
		args= new String[3];
		args[0]="C://Users/sys-user/Desktop/Certificate/json/licence.json";
		if(args.length>0) {
		    myMap.put("productName","Innometer");
		    myMap.put("productOwner","Techmango pvt ltd");

		    Scanner sc2 = new Scanner(System.in);
		    System.out.println("Enter your CustomerName : ");
		    String customerName = sc2.next();
		    myMap.put("customerName",customerName);

		    String activationDate = sdf.format(new Date());
		    myMap.put("activationDate",activationDate);
		    
		    myMap.put("dailyCount","0");
		    
		    Scanner sc6 = new Scanner(System.in);
		    System.out.println("Enter your ValidatityInDays : ");
		    String validatityInDays = sc6.next();
		    myMap.put("validatityInDays",validatityInDays);
		    
		    
		    Date currentDate = new Date();
	        System.out.println("date : " + sdf.format(currentDate));
		    LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

	        // plus one
	        localDateTime = localDateTime.plusYears(0).plusMonths(0).plusDays(Integer.valueOf(validatityInDays));
	        localDateTime = localDateTime.plusHours(0).plusMinutes(0).minusMinutes(0).plusSeconds(0);

	        // convert LocalDateTime to date
	        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

	        System.out.println("\nOutput : " + sdf.format(currentDatePlusOneDay));
		    
		    myMap.put("expiryDate",sdf.format(currentDatePlusOneDay));
		    
		    myMap.put("activated","TRUE");
		    
		    Scanner sc8 = new Scanner(System.in);
		    System.out.println("Enter your IPAddress : ");
		    String ipAddress = sc8.next();
		    myMap.put("ipAddress",ipAddress);
		    
		    Scanner sc9 = new Scanner(System.in);
		    System.out.println("Enter your MACAddress : ");
		    String macAddress = sc9.next();
		    myMap.put("macAddress",macAddress);
		    
		    Scanner sc10 = new Scanner(System.in);
		    System.out.println("Enter your HostName : ");
		    String hostName = sc10.next();
		    myMap.put("hostName",hostName);


			try {
				JSONObject jsonObject = new JSONObject(myMap.toString());
				System.out.println(jsonObject.toString());

				try (FileWriter file = new FileWriter(args[0])) {
					file.write(jsonObject.toString());
					System.out.println("\n");
					System.out.println("Successfully Copied JSON Object to File..." +args[0]);
				}} catch (JSONException e) {
			        e.printStackTrace();
			    }
		}else {
			System.out.println("Please Pass FileWriteArgument ");
		}
	    
	        
	    
	}
	
}
