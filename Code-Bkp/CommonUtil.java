package com.innothink.innometer.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class CommonUtil {

	public static String decrypt(String strToDecrypt) {
		try {
			String secretkey = "techmangolocal12";
			byte[] key = secretkey.getBytes(StandardCharsets.UTF_8);
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
	
	public static boolean checkExpireDate(String currentDate, String expireDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = sdf.parse(currentDate);
			Date date2 = sdf.parse(expireDate);
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(date1);
			cal2.setTime(date2);

			if (cal1.after(cal2)) {
				return true;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public static boolean macAddressValidate(String existMacAddress,String macAddress) {
		if(StringUtils.isNotBlank(existMacAddress) && StringUtils.isNotBlank(macAddress)) {
			if(existMacAddress.equalsIgnoreCase(macAddress)) {
				return true;
			}
				
		}
		return false;
		
	}
	
	public static String getMAC() {
		String mac = null;
		try {
			String OSName = System.getProperty("os.name");
			if (OSName.contains("Windows")) {
				mac = getWindowsMAC();
			} else {
				mac = getMAC4Linux("eth0");
				if (mac == null) {
					mac = getMAC4Linux("eth1");
					if (mac == null) {
						mac = getMAC4Linux("eth2");
						if (mac == null) {
							mac = getMAC4Linux("usb0");
						}
					}
				}
			}
		} catch (Exception e) {
			 e.printStackTrace(); 
		}
		return mac;
	}
	
	public static String getWindowsMAC(){
        try{
           InetAddress inetaddress=InetAddress.getLocalHost(); //Get LocalHost refrence
            
           //get Network interface Refrence by InetAddress Refrence
           NetworkInterface network = NetworkInterface.getByInetAddress(inetaddress); 
           byte[] macArray = network.getHardwareAddress();  //get Harware address Array
           StringBuilder str = new StringBuilder();
            
           // Convert Array to String 
           for (int i = 0; i < macArray.length; i++) {
                   str.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : ""));
           }
           String macAddress=str.toString();
        
           return macAddress; //return MAc Address
       }
       catch(Exception e){
           e.printStackTrace();  //print Exception StackTrace
           return null;
       } 
   }
	
	 public static String getMAC4Linux(String name){
	        try {
	            NetworkInterface network = NetworkInterface.getByName(name);
	            byte[] mac = network.getHardwareAddress();
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < mac.length; i++){
	                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
	            }
	            return (sb.toString());
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	            return null;
	        } 
	    } 
}
