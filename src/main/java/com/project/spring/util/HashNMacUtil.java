package com.project.spring.util;

import java.lang.ref.SoftReference;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class HashNMacUtil {


	public static final byte[] getHashSHA1(byte[] text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(text);
		byte[] result = md.digest();

		return result;
	}

	
	public static final byte[] getHMAC(SecretKey sk, String algorithm, byte[] text) throws NoSuchAlgorithmException,
			NoSuchProviderException, InvalidKeyException {
		Mac mac = Mac.getInstance(algorithm);
		mac.init(sk);
		byte[] result = mac.doFinal(text);

		return result;
	}


	public static final byte[] getHmacSHA1WithBytesKey(byte[] key, byte[] text) throws NoSuchAlgorithmException,
			InvalidKeyException {
		Mac mac = Mac.getInstance("HmacSHA1");

		SoftReference<SecretKey> secretKey = new SoftReference<SecretKey>(new SecretKeySpec(key, "HmacSHA1"));

		mac.init(secretKey.get());

		byte[] result = mac.doFinal(text);
		
		secretKey = null;
		
		return result;
	}
	
	
	public static final byte[] getSha1HashChain(int count, byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		byte[] hashedData = data;
		
		for( int i = 0; i < count; i++) {
			md.update(hashedData);
			hashedData = md.digest();
		}
		
		return hashedData;
	}

	
	public static byte[] getMD5(byte[] text) throws NoSuchAlgorithmException  {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text);
        byte[] result = md.digest();
        
        return result;

	}

 	
	public static byte[] getHashSHA256(byte[] text) throws NoSuchAlgorithmException {
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text);
        byte[] result = md.digest();
        
        return result;
  }
	
	public static String EncBySha256(String data) throws Exception {
		  String retVal = "";
		  try {
		  	MessageDigest md = MessageDigest.getInstance("SHA-256");
		  	md.update(data.getBytes());

		  	byte byteData[] = md.digest();
		  	StringBuffer sb = new StringBuffer();
		    
		    for(int i=0; i<byteData.length; i++) {
		      sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		    }

		    StringBuffer hexString = new StringBuffer();
		    for(int i=0; i<byteData.length;i++) {
		    String hex = Integer.toHexString(0xff & byteData[i]);
		    if(hex.length() == 1) {
		      hexString.append('0');
		    }
		      hexString.append(hex);
		    }

		  	retVal = hexString.toString();
		  } catch(NoSuchAlgorithmException e){
		  	System.out.println("EncBySHA256 Error:" + e.toString());
		  }
		  return retVal;
		}
	
}