import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CertificateFileDecryption {
	
	public static String decrypt(String strToDecrypt,String secretKeyStr) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			byte[] key = secretKeyStr.getBytes(StandardCharsets.UTF_8);
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
			return decryptedString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		
		String line = null;
		FileReader fileReader;
		try {
				if(args.length==2) {
					String inputFileName =args[0];
					String key = args[1];
					//String inputFileName = "C://Users/smi-user/Desktop/Certificate/innometer.cert";
					fileReader = new FileReader(inputFileName);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					StringBuffer buf = new StringBuffer();
					while ((line = bufferedReader.readLine()) != null) {
						buf.append(line);
					}
					
					byte[] valueDecoded = Base64.decodeBase64(buf.toString());
					System.out.println("\n");
					System.out.println("Base 64 Decode >>>>"+new String(valueDecoded));
					System.out.println("\n");
					
					String decryptedStr = decrypt(new String(valueDecoded),key);
					System.out.println("AES Decription ::  " + decryptedStr);
					System.out.println("\n");
				}else {
					System.out.println("Argument not passed");
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}

}
