package com.nik.di.demo;

import com.nik.di.bean.HelloBean;
import com.nik.di.factory.AnnotationBeanFactory;
import com.nik.di.factory.BeanFactory;

public class AnnotationDemo {
	public static void main(String[] args) {
        BeanFactory factory = new AnnotationBeanFactory("com.nik.di.bean");
        Object obj = factory.getBean("hello");
        System.out.println("Get object for hello bean: " + obj);
        

        HelloBean bean = factory.getBean("hello", HelloBean.class);
        bean.sayHello();
    }
}
