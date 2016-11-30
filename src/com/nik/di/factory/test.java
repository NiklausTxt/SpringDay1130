package com.nik.di.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test {
	
	public static void main(String[] args) {
		String packageName = "com.nik.di.factory";
		scanPackage(packageName);
	}
	public static List<String> scanPackage(String packageName){
		List<String> list = new ArrayList<>();
		
		System.out.println(packageName);
		String packagePath = packageName.replace(".", File.separator);
		System.out.println(packagePath);
		
		String systemPath = ClassLoader.getSystemResource("").getPath();
		System.out.println(systemPath);
		File systemDir = new File(systemPath);
		System.out.println(systemDir.getAbsolutePath());
		
		File packageDir = new File(systemPath,packagePath);
		System.out.println(packageDir.getAbsolutePath());
		
		list = listClass(packageDir,systemDir);
		return list;
		
	}
	private static List<String> listClass(File packageDir, File systemDir) {
		List<String> list = new ArrayList<>();
		for(File file : packageDir.listFiles()){
			String fileName = file.getAbsolutePath();
			if(file.isDirectory()){
				System.out.println("is a directory:" +fileName);
				list.addAll(listClass(file, systemDir));
				continue;
			}
			if(!fileName.endsWith(".class")){
				System.out.println("not .class:" +fileName);
				continue;
			}
			
			String systemPath = systemDir.getAbsolutePath();
            String className = fileName.replace(".class", "")
                    .replace(systemPath + File.separator, "").replace(File.separator, ".");
            System.out.println(className);
            list.add(className);
		}
		return list;
		
	}
}
