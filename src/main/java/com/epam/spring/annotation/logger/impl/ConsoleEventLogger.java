package com.epam.spring.annotation.logger.impl;

import com.epam.spring.annotation.domain.Event;
import com.epam.spring.annotation.logger.EventLogger;
import org.springframework.stereotype.Component;

@Component("consoleEventLogger")
public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
