package com.epam.spring.javaconfig.logger.impl;

import com.epam.spring.javaconfig.logger.EventLogger;
import com.epam.spring.javaconfig.domain.Event;
import org.springframework.stereotype.Component;

@Component("consoleEventLogger")
public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
