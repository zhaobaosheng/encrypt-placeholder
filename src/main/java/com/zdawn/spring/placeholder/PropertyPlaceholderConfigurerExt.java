package com.zdawn.spring.placeholder;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/**
 * 对某些属性值进行解密处理
 * @author zhaobaosheng
 *
 */
public class PropertyPlaceholderConfigurerExt extends PropertyPlaceholderConfigurer {
	//加密key
	private List<String> encryptPropsKey = null;
	
	@SuppressWarnings("unused")
	private AESCipherUtil aesUtil;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		try {
			if(encryptPropsKey!=null){
				for (String temp_key : encryptPropsKey) {
					Object obj = props.get(temp_key);
					if(obj!=null && !obj.equals("")){
						logger.debug(obj.toString()+"  "+AESCipherUtil.decode(obj.toString()));
						props.setProperty(temp_key, AESCipherUtil.decode(obj.toString()));
					}
				}
			}
			super.processProperties(beanFactoryToProcess, props);
		} catch (Exception e) {
			throw new BeanCreationException("decoding placeholder error "+e.getMessage());
		}
	}

	public void setEncryptPropsKey(List<String> encryptPropsKey) {
		this.encryptPropsKey = encryptPropsKey;
	}

	public void setAesUtil(AESCipherUtil aesUtil) {
		this.aesUtil = aesUtil;
	}
}
