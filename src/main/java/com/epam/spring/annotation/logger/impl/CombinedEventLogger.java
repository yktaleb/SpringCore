package com.epam.spring.annotation.logger.impl;

import com.epam.spring.annotation.domain.Event;
import com.epam.spring.annotation.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("combinedEventLogger")
public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    @Autowired
    public CombinedEventLogger(@Qualifier("loggerList") List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(eventLogger -> eventLogger.logEvent(event));
    }
}
