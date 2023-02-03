package com.assignment.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequestBody<T>{

    @NotEmpty
    private String requestId;
    @NotEmpty
    private String channelId;
    @NotEmpty
    private String requestTimeStamp;
    @Valid
    private T data;
}
