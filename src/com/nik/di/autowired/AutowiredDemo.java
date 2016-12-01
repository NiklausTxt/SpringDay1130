package com.nik.di.autowired;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiredDemo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/nik/di/resource/autowired.xml");

		GreetingBean gb = context.getBean("greetingBean", GreetingBean.class);
		String value = gb.printGreeting();
		System.out.println(value);

		XmlBean xmlBean = context.getBean("xmlBean", XmlBean.class);
		xmlBean.print();
		xmlBean.printWiredBean();

		context.close();
	}
}
