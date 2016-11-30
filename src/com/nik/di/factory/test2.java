package com.nik.di.factory;

import java.lang.reflect.Field;

import com.nik.di.annotation.Bean;

public class test2 {
	public static void main(String[] args) {
			
		
	}

	protected static void resovleDependence(Object instance) {
		Class<?> clazz = instance.getClass();

		Field[] fields = clazz.getDeclaredFields();
		for(Field f:fields){
			if(!f.isAnnotationPresent(Bean.class)){
				continue;
			}
			System.out.println("是否使用注解："+f.isAnnotationPresent(Bean.class));
			Bean injectedBean = f.getAnnotation(Bean.class);
			String injectedBeanName = injectedBean.value();
			System.out.println(injectedBeanName);
		}
	}
}
