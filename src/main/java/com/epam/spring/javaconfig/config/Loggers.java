package com.epam.spring.javaconfig.config;

import com.epam.spring.xml.logger.EventLogger;
import com.epam.spring.xml.logger.impl.CacheFileEventLogger;
import com.epam.spring.xml.logger.impl.FileEventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {
        "classpath:client.properties"
})
public class Loggers {

    @Bean(initMethod = "init")
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("D:\\IdeaProjects\\logs.txt");
    }

    @Bean(destroyMethod = "destroy")
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("D:\\IdeaProjects\\logs.txt", 2);
    }
}
