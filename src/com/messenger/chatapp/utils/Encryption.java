//to encrypt password
package com.messenger.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
	public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException{
		String encryptedPassword = null;
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plainPassword.getBytes());
		StringBuffer sb = new StringBuffer();
		byte[] encrypt = messageDigest.digest();
		for(byte b : encrypt) {
			sb.append(b);
		}
		encryptedPassword = sb.toString();
			
		return encryptedPassword;
	}
}
