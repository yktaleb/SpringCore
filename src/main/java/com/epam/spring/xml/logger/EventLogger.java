package com.epam.spring.xml.logger;

import com.epam.spring.xml.domain.Event;

public interface EventLogger {
    void logEvent(Event event);
}
