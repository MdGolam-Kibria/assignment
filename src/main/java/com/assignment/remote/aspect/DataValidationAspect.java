package com.assignment.remote.aspect;


import com.assignment.remote.dto.BaseResponseBody;
import com.assignment.remote.utils.ResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class DataValidationAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(DataValidationAspect.class.getName());

    @Around("@annotation(com.assignment.remote.annotation.ValidateData) && args(..)")
    public BaseResponseBody<?> validateData(ProceedingJoinPoint joinPoint) throws JsonProcessingException {
        Object[] signatures = joinPoint.getArgs();
        BindingResult result = null;
        for (Object signature : signatures) {
            if (signature instanceof BindingResult) {
                result = (BindingResult) signature;
                break;
            }
        }
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureResponse(result, "Bean Binding error");
        }
        try {
            return (BaseResponseBody<?>) joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage());
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}
