package com.tomojo.models.http;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ControllerResponse {

    private HttpResponseStatus status;
    private String message;
    private Object body;

    public ControllerResponse() {

    }

    public ControllerResponse(HttpResponseStatus status, String message, Object body) {
        super();
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public ControllerResponse(HttpResponseStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getBody() {
        return body;
    }

}
