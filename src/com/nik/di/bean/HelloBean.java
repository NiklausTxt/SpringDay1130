package com.nik.di.bean;

import com.nik.di.annotation.Bean;

@Bean("hello")
public class HelloBean {
    
    @Bean("person")
    private Person person;

    public void sayHello() {
        System.out.println("Hello world, " + person);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    
}