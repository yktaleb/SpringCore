package com.epam.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");
        App app = (App) context.getBean("app");

        Event event1 = (Event) context.getBean("event");
        event1.setMsg("user 1");

        Event event2 = (Event) context.getBean("event");
        event2.setMsg("user 2");

        app.logEvent(EventType.INFO, event1);
        context.close();
    }

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        event.setMsg(event.getMsg().replaceAll(
                String.valueOf(client.getId()), client.getGreeting() + client.getFullName()));
        logger.logEvent(event);
    }
}
