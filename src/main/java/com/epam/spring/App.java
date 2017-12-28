package com.epam.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public EventLogger eventLogger;
    public Client client;

    public App(EventLogger eventLogger, Client client) {
        this.eventLogger = eventLogger;
        this.client = client;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        App app = (App) context.getBean("app");

        Event event1 = (Event) context.getBean("event");
        event1.setMsg("Event form user 1");

        Event event2 = (Event) context.getBean("event");
        event2.setMsg("Event form user 2");

        app.logEvent(event1);
        app.logEvent(event2);
        app.logEvent(event2);
        context.close();
    }

    private void logEvent(Event event) {
        event.setMsg(event.getMsg().replaceAll(String.valueOf(client.getId()), client.getFullName()));
        eventLogger.logEvent(event);
    }
}
