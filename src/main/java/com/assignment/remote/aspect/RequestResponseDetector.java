package com.assignment.remote.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class RequestResponseDetector {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @AfterReturning(value = ("execution(public * com.assignment.remote.controller.*.*(..))"),
            returning = "returnValue")
    public void endpointAfterReturningProcess(JoinPoint p, Object returnValue) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            if (p.getArgs().length != 0) {

                LOGGER.info("\n\n\nRequest Object: \n" + mapper.writeValueAsString(p.getArgs()[0]));

                LOGGER.info("\nResponse Object: \n" + mapper.writeValueAsString(returnValue));
            }
        } catch (JsonProcessingException e) {
            LOGGER.info("Request Object or Response Object Map Time ERROR:{}", e.getMessage());
        }
        LOGGER.info("\nControllerName : " + p.getTarget().getClass().getSimpleName() + "\n MethodName : " + p.getSignature().getName() + " \nEND\n\n\n");
    }
}
