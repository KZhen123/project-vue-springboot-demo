package com.project.constant;

public final class ResponseCodes {
    private ResponseCodes() {
    }

    public final static Integer SUCCESS = 200;
    public final static Integer FAIL = 500;
    public final static Integer PARAMETERS_ERROR = 1000;
    public final static Integer PROCESS_ERROR = 3000;
    public final static Integer EXCEPTION = 7000;
    public final static Integer ANONYMOUS = 8000;
    public final static Integer TOKEN_EMPTY = 8001;
    public final static Integer TOKEN_EXPIRED = 8002;
    public final static Integer ILLEGAL_ACCESS = 8003;
    public final static Integer UNAUTHORIZED = 8008;
}

