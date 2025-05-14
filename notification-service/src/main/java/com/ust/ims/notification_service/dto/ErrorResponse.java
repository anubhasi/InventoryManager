package com.ust.ims.notification_service.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String msg;
    private String statusCode;
    private LocalDateTime timeStamp;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ErrorResponse(String msg, String statusCode, LocalDateTime timeStamp) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
    }
}
