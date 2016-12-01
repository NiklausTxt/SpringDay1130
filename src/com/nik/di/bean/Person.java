package com.nik.di.bean;

import com.nik.di.annotation.Bean;

@Bean(value="person")
public class Person {

    private String name = "No ONE";
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
