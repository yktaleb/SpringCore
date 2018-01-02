package com.epam.spring.annotation.logger;

import com.epam.spring.annotation.domain.Event;

public interface EventLogger {
    void logEvent(Event event);
}
