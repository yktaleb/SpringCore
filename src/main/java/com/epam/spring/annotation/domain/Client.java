package com.epam.spring.annotation.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("client")
public class Client {
    private Long id;
    private String fullName;
    private String greeting;

    @Autowired
    public Client(@Value("${id}") Long id,
                  @Value("${name}") String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    @Autowired
    public void setGreeting(@Value("${greeting}")String greeting) {
        this.greeting = greeting;
    }
}
