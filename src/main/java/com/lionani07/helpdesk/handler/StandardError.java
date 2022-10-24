package com.lionani07.helpdesk.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class StandardError {

    private String path;
    private int status;
    private String error;
    private String message;
    private Long timestamp;
}
