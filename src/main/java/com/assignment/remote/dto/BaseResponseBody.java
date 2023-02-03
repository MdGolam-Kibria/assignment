package com.assignment.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseBody<T> {

    private String requestId;
    private String channelId;
    private String requestTimestamp;
    private String responseId;
    private String responseTimestamp;
    private String responseCode;
    private List<String> responseMessages;
    private T data;
}
