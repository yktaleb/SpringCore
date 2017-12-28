package com.epam.spring;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), "UTF-8", true );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new Exception();
        }
    }
}
