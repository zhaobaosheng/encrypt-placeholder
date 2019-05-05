package com.zdawn.spring.placeholder;

import java.util.Properties;

public class DecryptProperties extends Properties {
	private static final long serialVersionUID = -4992722714685900397L;
	
	private String keyPrefix = "enc";
	
	public DecryptProperties(){
	}
	
	public DecryptProperties(String ...configNames){
		loadProperties(configNames);
	}
	
	public void loadProperties(String ...configNames){
		try {
			for (String temp : configNames) {
				 this.load(DecryptProperties.class.getClassLoader().getResourceAsStream(temp));
			}
			for (String key : stringPropertyNames()) {
				if(key.startsWith(keyPrefix)){
					put(key, AESCipherUtil.decode(getProperty(key)));
//					System.out.println(key + "=" + getProperty(key));
				}
		    }
		} catch (Exception e) {
			System.err.println("DecryptProperties construction init error "+e);
		}
	}
	
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
}
