package com.epam.spring.xml.logger.impl;

import com.epam.spring.xml.domain.Event;
import com.epam.spring.xml.logger.EventLogger;

import java.util.List;

public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(eventLogger -> eventLogger.logEvent(event));
    }
}
