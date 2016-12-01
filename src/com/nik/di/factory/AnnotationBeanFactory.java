package com.nik.di.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.nik.di.annotation.Bean;
import com.nik.di.annotation.Singleton;

public class AnnotationBeanFactory extends AbstractBeanFactory {

	public AnnotationBeanFactory(String packageName) {
		refreshBeans(packageName);
		resolveDependence();
	}

	private void resolveDependence() {
		for (Object object : beanMap.values()) {
			resovleDependence(object);
		}
		for (Object object : prototypeMap.values()) {
			resovleDependence(object);
		}
	}

	private void refreshBeans(String packageName) {
		List<String> classList = scanPackage(packageName);

		for (String className : classList) {
			try {
				Class<?> c = Class.forName(className);
				if (!c.isAnnotationPresent(Bean.class)) {
					System.out.println("Skipped the non-bean class: " + c.getName());
				}

				Bean bean = c.getAnnotation(Bean.class);
				String beanName = bean.value();

				boolean isSingleton = true;
//				if (c.isAnnotationPresent(Singleton.class)) {
//					Singleton singleton = (Singleton) c.getAnnotation(Singleton.class);
//					isSingleton = singleton.value();
//				}
				if (c.isAnnotationPresent(Bean.class)) {
					Bean singleton = (Bean) c.getAnnotation(Bean.class);
					isSingleton = singleton.value2();
				}

				distinctBean(beanName, c, isSingleton);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<String> scanPackage(String packageName) {
		List<String> list = new ArrayList<>();

		System.out.println(packageName);
		String packagePath = packageName.replace(".", File.separator);
		System.out.println(packagePath);

		String systemPath = ClassLoader.getSystemResource("").getPath();
		System.out.println(systemPath);
		File systemDir = new File(systemPath);
		System.out.println(systemDir.getAbsolutePath());

		File packageDir = new File(systemPath, packagePath);
		System.out.println(packageDir.getAbsolutePath());

		list = listClass(packageDir, systemDir);
		return list;

	}

	private List<String> listClass(File packageDir, File systemDir) {
		List<String> list = new ArrayList<>();
		if(packageDir.listFiles()==null){
			throw new RuntimeException("error");
		}
		for (File file : packageDir.listFiles()) {
			String fileName = file.getAbsolutePath();
			if (file.isDirectory()) {
				System.out.println("is a directory:" + fileName);
				list.addAll(listClass(file, systemDir));
				continue;
			}
			if (!fileName.endsWith(".class")) {
				System.out.println("not .class:" + fileName);
				continue;
			}

			String systemPath = systemDir.getAbsolutePath();
			String className = fileName.replace(".class", "").replace(systemPath + File.separator, "")
					.replace(File.separator, ".");
			System.out.println(className);
			list.add(className);
		}
		return list;

	}
}
