package com.msg.msg.encryption;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoConverter {

	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final byte[] KEY = "NsP#ikr0Ol$1697!".getBytes();

	public static String encrypt(String pwd) {
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			return new String(Base64.getEncoder().encode(c.doFinal(pwd.getBytes())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
