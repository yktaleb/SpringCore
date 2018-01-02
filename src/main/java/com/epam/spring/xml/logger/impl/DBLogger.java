package com.epam.spring.xml.logger.impl;

import com.epam.spring.xml.domain.Event;
import com.epam.spring.xml.logger.EventLogger;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBLogger implements EventLogger {
    private JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO event(id, message) VALUES(?,?)",
                event.getId(), event.getMsg());
    }
}
