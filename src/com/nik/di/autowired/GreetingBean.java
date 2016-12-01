package com.nik.di.autowired;

import org.springframework.beans.factory.annotation.Autowired;
public class GreetingBean {
	@Autowired
    private Greeting greeting;
    
    public String printGreeting() {
        return greeting.greeting();
    }
}
