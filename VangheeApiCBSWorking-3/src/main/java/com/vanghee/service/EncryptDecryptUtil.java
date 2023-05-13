package com.vanghee.service ;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecryptUtil {
public static String createChecksum(String input) throws Exception {
				MessageDigest md;
				StringBuilder hexString = new StringBuilder();
			try {
				md = MessageDigest.getInstance("SHA-256");
				md.update(input.getBytes(StandardCharsets.UTF_8));
				byte[] hash = md.digest();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
				hexString.append('0');
				hexString.append(hex);
			}
			}catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
			}
			return hexString.toString().toUpperCase();
}
public static String encryptAES256AndBase64(String encryptionKey, String iv, String jsonBody)throws Exception {
				byte[] encbyte = null;
			try {
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
				SecretKeySpec key = new
				SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
				cipher.init(Cipher.ENCRYPT_MODE, key, new
				IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
				encbyte = cipher.doFinal(jsonBody.getBytes(StandardCharsets.UTF_8));
			} catch (Exception ex) {
			throw new Exception(ex.getMessage());
			}
			return Base64.getEncoder().encodeToString(encbyte);
}

public static String decryptBase64EncodedAES256(String encryptionKey, String iv, String inputParam) throws Exception {
			String decodedOutput;
			try {
				byte[] decodedInput = Base64.getDecoder().decode(inputParam);
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
				SecretKeySpec key = new
				SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
				cipher.init(Cipher.DECRYPT_MODE, key, new
				IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
				decodedOutput = new String(cipher.doFinal(decodedInput), StandardCharsets.UTF_8);
			} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException |InvalidKeyException
			| InvalidAlgorithmParameterException | IllegalBlockSizeException |BadPaddingException e) {
				
			throw new Exception(e.getMessage());
			
			}
			return decodedOutput;
			}
}
