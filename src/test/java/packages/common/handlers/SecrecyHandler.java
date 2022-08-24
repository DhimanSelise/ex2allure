package packages.common.handlers;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecrecyHandler {

	private static final String secret = "Tg2Nn7wUZOQ6Xc+1lenkZTQ9a68aHa7R8BRiqJBCIX6o";
	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static void setKey(final String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String Encrypt(final String strToEncrypt) {
		return Encrypt(strToEncrypt, secret);
	}
	public static String Encrypt(final String strToEncrypt, final String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String Decrypt(final String strToDecrypt) {
		return Decrypt(strToDecrypt, secret);
	}
		public static String Decrypt(final String strToDecrypt, final String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}
