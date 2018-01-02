package com.epam.spring.xml.aspect;

import com.epam.spring.xml.domain.Event;
import com.epam.spring.xml.logger.EventLogger;
import com.epam.spring.xml.logger.impl.ConsoleEventLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggerAspect {

    private Map<Class<?>, Integer> counter;
    private int maxCountUseConsoleLogger;
    private EventLogger replaceableLogger;

    public LoggerAspect(EventLogger replaceableLogger, int maxCountUseConsoleLogger) {
        this.replaceableLogger = replaceableLogger;
        this.maxCountUseConsoleLogger = maxCountUseConsoleLogger;
        counter = new HashMap<>();
    }

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers(){}

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz) + 1);
    }

    @Around("allLogEventMethods() && within(com.epam.spring.xml.logger.impl.ConsoleEventLogger) && args(event))")
    public void aroundLogEvent(ProceedingJoinPoint joinPoint, Object event) throws Throwable {
        Integer count = counter.get(ConsoleEventLogger.class);
        if (count == null || count < maxCountUseConsoleLogger) {
            joinPoint.proceed(new Object[]{event});
        } else {
            replaceableLogger.logEvent((Event) event);
        }
    }

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }
}
