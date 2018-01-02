package com.epam.spring.javaconfig.logger;

import com.epam.spring.javaconfig.domain.Event;

public interface EventLogger {
    void logEvent(Event event);
}
