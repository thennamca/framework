import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CertificateFileGeneration {
	
	public static String encrypt(String strToEncrypt,String secretKeyStr) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			byte[] key = secretKeyStr.getBytes(StandardCharsets.UTF_8);
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
			return encryptedString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		
		//CipherUtils cipher = new CipherUtils();
		/*String inputFileName = "C://Users/smi-user/Desktop/Certificate/certificate.json";
		String fileName = "C://Users/smi-user/Desktop/Certificate/innometer.cert";*/
		String line = null;
		FileReader fileReader;
		try {
			if(args.length==3) {
				
				if(args[2].length()==16) {
					String inputFileName = args[0];
					String fileName = args[1];
					String key = args[2];
					
					fileReader = new FileReader(inputFileName);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					StringBuffer buf = new StringBuffer();
					while ((line = bufferedReader.readLine()) != null) {
						buf.append(line);

					}
					System.out.println("\n");
					System.out.println("Before Encrypt Orginal Text"+buf);
					System.out.println("\n");
					String encryptedStr = encrypt(buf.toString(),key);
					System.out.println("AES encription ::   " + encryptedStr);
					System.out.println("\n");

					byte[] bytesEncoded = Base64.encodeBase64(encryptedStr.getBytes());
					System.out.println("BASE 64 Encription :: "+new String(bytesEncoded));
					System.out.println("\n");
					
					
					/*byte[] valueDecoded = Base64.decodeBase64(bytesEncoded);
					System.out.println("valueDecoded>>>"+new String(valueDecoded));*/
					
					try (FileOutputStream fos = new FileOutputStream(fileName)) {
						fos.write(bytesEncoded);
						if (fos != null)
							fos.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("3 rd argument should be 16 charactor");
				}
				
			}else {
				System.out.println("Arugument not passed");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}

}
