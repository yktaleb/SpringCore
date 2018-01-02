package com.epam.spring.annotation.logger.impl;

import com.epam.spring.annotation.domain.Event;
import com.epam.spring.annotation.logger.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component("fileEventLogger")
public class FileEventLogger implements EventLogger {
    protected String fileName;
    protected File file;

    public FileEventLogger(@Value("D:\\IdeaProjects\\logs.txt")
                                   String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", "UTF-8", true );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    protected void init() throws Exception {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new Exception();
        }
    }
}
