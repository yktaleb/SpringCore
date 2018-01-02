package com.epam.spring.xml.logger.impl;

import com.epam.spring.xml.domain.Event;
import com.epam.spring.xml.logger.EventLogger;

public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
