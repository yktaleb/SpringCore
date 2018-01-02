package com.epam.spring.javaconfig.logger.impl;

import com.epam.spring.javaconfig.domain.Event;
import com.epam.spring.javaconfig.logger.EventLogger;
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
