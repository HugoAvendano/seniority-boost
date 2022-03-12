package com.havendan.rooftop.academy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.crypto.spec.SecretKeySpec;



public class Utils {
	
	public static final  String generateHashMD5(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		  MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update("texto a cifrar".getBytes());
	      byte[] bytes = md.digest();
	      StringBuilder strCryptMD5 = new StringBuilder(bytes.length);

	      // Se escribe byte a byte en hexadecimal
	      for (byte b : bytes) {
	         System.out.print(Integer.toHexString(0xFF & b));
	         strCryptMD5.append(Integer.toHexString(0xFF & b));
	         
	      }
	      System.out.println();

	      // Se escribe codificado base 64. Se necesita la librería
	      // commons-codec-x.x.x.jar de Apache	      
	     
	      return strCryptMD5.toString() ;
    }
	
	public static final HashMap<String, Integer> generateResult (String text, int chars) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0 ; i < text.length() - chars; i++) {
			String pivot = text.substring(i, i+chars);
			if (map.get(pivot) == null) {
				//String backup = text;
				int repetition = getCantRepetition(pivot,text);
				map.put(pivot, repetition);
			}
		}
		
		System.out.println(map.toString());
		return map;
		
	}

	private static int getCantRepetition(String subcadena , String text) {
		int repeticiones= 0;
		while (!text.isEmpty() && text.indexOf(subcadena) != -1) {
			repeticiones ++;
			text = text.replaceFirst(Pattern.quote(subcadena), "");
		}
		return repeticiones;
	}
	
	
	
	
	
}
