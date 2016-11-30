package com.nik.di.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.nik.di.annotation.Bean;


public abstract class AbstractBeanFactory implements BeanFactory{
	
	protected Map<String, Object> beanMap = new HashMap<>();
	protected Map<String, Class<?>> prototypeMap = new HashMap<>();
	
	protected void distinctBean(String name, Class<?> clazz, boolean isSingleton){
		if(!isSingleton){
			prototypeMap.put(name, clazz);
		}else{
			try {
				Object object = clazz.newInstance();
				beanMap.put(name, object);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	@Override
	public Object getBean(String name) {
		if(beanMap.containsKey(name)){
			return beanMap.get(name);
		}
		Class<?> clazz = prototypeMap.get(name);
		Object obj=null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBean(String name, Class<T> clazz) {
		Object obj = getBean(name);
		if(obj!=null){
			return (T) obj;
		}
		obj=null;
		return (T) obj;
	}
	
	protected void resovleDependence(Object instance) {
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
			
			Object injectedObject = getBean(injectedBeanName);
			
			if(injectedObject==null){
				throw new RuntimeException("The injected bean is not defined! Bean name: " + injectedBeanName);
			}
			
			invokeSetterMethod(instance, f, injectedObject);
		}
	}


	private void invokeSetterMethod(Object instance, Field field, Object injectedObject) {
		assert(instance != null && field != null);
		Method method = getSetterMethodByField(field, instance.getClass());
		try {
			method.invoke(instance, injectedObject);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Failed to invoke the setter method for field: "
	                + field.getName());
		}
	}


	private Method getSetterMethodByField(Field field, Class<?> clazz) {
		String fileName = field.getName();
		StringBuilder sb = new StringBuilder(fileName);
		if(Character.isLowerCase(fileName.charAt(0))){
			sb.setCharAt(0, Character.toUpperCase(fileName.charAt(0)));
		}
		
		String setterMethodName = "set"+sb.toString();
		
		try {
			Method method = clazz.getMethod(setterMethodName, field.getType());
			return method;
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("No Setter method defined for field: " + field.getName()
            + ", the expected setter method is: " + setterMethodName);
		} 
		
	}
	
	
}
