package com.epam.spring.javaconfig.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Component("event")
@Scope(value = "prototype")
public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    @Autowired
    public Event(Date date, DateFormat dateFormat) {
        id = new Random().nextInt();
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public static boolean isDay() {
        int hour = LocalDateTime.now().getHour();
        if (hour > 8 && hour <=17) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
