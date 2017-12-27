package com.epam.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public ConsoleEventLogger consoleEventLogger;
    public Client client;

    public App(ConsoleEventLogger consoleEventLogger, Client client) {
        this.consoleEventLogger = consoleEventLogger;
        this.client = client;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        App app = (App) context.getBean("app");

        app.logEvent("Hello world with 1");
        app.logEvent("Hello world with 2");
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        consoleEventLogger.logEvent(message);
    }
}
