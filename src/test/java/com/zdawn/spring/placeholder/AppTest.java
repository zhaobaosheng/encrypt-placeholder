package com.zdawn.spring.placeholder;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest{
	public static void main(String[] agr){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestBean test = context.getBean("testBean",TestBean.class);
		System.out.println("name="+test.getName());
	}
}
