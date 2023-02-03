package com.assignment.remote.utils;


import com.assignment.remote.dto.BaseResponseBody;
import com.assignment.remote.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    private static List<ErrorResponseDto> getCustomError(BindingResult result) {
        List<ErrorResponseDto> dtoList = new ArrayList<>();
        result.getFieldErrors().forEach(fieldError -> {
            ErrorResponseDto dto = ErrorResponseDto.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage()).build();
            dtoList.add(dto);
        });
        return dtoList;
    }

    public static BaseResponseBody<?> getFailureResponse(BindingResult result, String message) {


        return BaseResponseBody.builder()
                .responseMessages(getCustomError(result).stream().map(s -> s.getField() + " " + s.getMessage()).collect(Collectors.toList()))
                .responseCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .responseTimestamp(String.valueOf(new Date().getTime()))
                .build();
    }

    public static BaseResponseBody<?> getFailureResponse(HttpStatus status, String message) {
        return BaseResponseBody.builder()
                .responseMessages(Collections.singletonList(message))
                .responseCode(String.valueOf(status.value()))
                .responseTimestamp(String.valueOf(new Date().getTime()))
                .build();
    }


    public static BaseResponseBody<?> getFailureResponse(HttpStatus status, String message, Object content) {
        return BaseResponseBody.builder()
                .responseMessages(Collections.singletonList(message))
                .data(content)
                .responseCode(String.valueOf(status.value()))
                .responseTimestamp(String.valueOf(new Date().getTime()))
                .build();
    }


    public static BaseResponseBody<?> getSuccessResponse(HttpStatus status, String message, Object content) {
        return BaseResponseBody.builder()
                .responseMessages(Collections.singletonList(message))
                .data(content)
                .responseCode(String.valueOf(status.value()))
                .responseTimestamp(String.valueOf(new Date().getTime()))
                .build();
    }

    
}
