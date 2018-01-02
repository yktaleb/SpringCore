package com.epam.spring.javaconfig;

import com.epam.spring.javaconfig.domain.Client;
import com.epam.spring.javaconfig.domain.Event;
import com.epam.spring.javaconfig.domain.EventType;
import com.epam.spring.javaconfig.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("app")
public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    @Autowired
    public App(@Qualifier("client") Client client,
               @Value("#{T(com.epam.spring.javaconfig.domain.Event).isDay() ? consoleEventLogger : cacheFileEventLogger}")
                       EventLogger defaultLogger) {
        this.client = client;
        this.defaultLogger = defaultLogger;
//        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("annotationContext.xml");
        App app = (App) context.getBean("app");

        Event event1 = (Event) context.getBean("event");
        event1.setMsg("user 1");

        Event event2 = (Event) context.getBean("event");
        event2.setMsg("user 2");

        app.logEvent(EventType.ERROR, event1);
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

    @Autowired
    public void setLoggers(    @Qualifier("loggerMap")
                                           Map<EventType, EventLogger> loggers) {
        this.loggers = loggers;
    }
}
