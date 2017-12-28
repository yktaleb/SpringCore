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

        Event event1 = (Event) context.getBean("event");
        event1.setMsg("Event form user 1");

        Event event2 = (Event) context.getBean("event");
        event2.setMsg("Event form user 2");

        app.logEvent(event1);
        app.logEvent(event2);
    }

    private void logEvent(Event event) {
        event.setMsg(event.getMsg().replaceAll(String.valueOf(client.getId()), client.getFullName()));
        consoleEventLogger.logEvent(event);
    }
}
