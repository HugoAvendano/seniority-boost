package com.havendan.rooftop.academy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



public class Utils {
	
	public static final  String generateHashMD5(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		  MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(clave.getBytes());
	      byte[] bytes = md.digest();
	      StringBuilder strCryptMD5 = new StringBuilder(bytes.length);

	      // Se escribe byte a byte en hexadecimal
	      for (byte b : bytes) {
	         System.out.print(Integer.toHexString(0xFF & b));
	         strCryptMD5.append(Integer.toHexString(0xFF & b));
	         
	      }            
	     
	      return strCryptMD5.toString() ;
    }
	
	public static final String generateResult (String text, int chars) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (text.length() == chars) {
			map.put(text, 1);
			
		}else {
		
			for (int i = 0 ; i < text.length() - chars; i++) {
				String pivot = text.substring(i, i+chars);
				
				if (map.get(pivot) == null) {				
					int repetition = getCantRepetition(pivot,text);
					map.put(pivot, repetition);
				}
			}		
						
		}
		return mapToString(map);
	}

	private static int getCantRepetition(String subcadena , String text) {
		int repeticiones= 0;
		while (!text.isEmpty() && text.indexOf(subcadena) != -1) {
			repeticiones ++;
			int indice = text.indexOf(subcadena);
			text = text.substring(indice + subcadena.length() - 1) ;			
		}
		return repeticiones;
	}
	
	public static Map<String, String> stringToMap(String text){
		text = text.replace("{","").replace("}", "");
		HashMap<String, String> map = (HashMap<String, String>) Arrays.asList(text.split(",")).stream().map(s -> s.split("=")).collect(Collectors.toMap(e -> e[0], e->e[1]));		
		return map;
		
	}
	
	public static String mapToString (HashMap<String, Integer> map) {
		String mapAsString = map.keySet().stream()
			      .map(key -> key + "=" + map.get(key))
			      .collect(Collectors.joining(",", "{", "}"));
		
		return mapAsString;
	}
	
	
	
}
