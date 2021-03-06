package com.epam.spring.xml.logger.impl;

import com.epam.spring.xml.domain.Event;
import com.epam.spring.xml.logger.EventLogger;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    protected String fileName;
    protected File file;

    public FileEventLogger(String fileName) {
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

    protected void init() throws Exception {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new Exception();
        }
    }
}
