package com.retro.logger.model;

import java.io.Serializable;

public class ExceptionModel implements Serializable {

    private String ID;
    private String STACKTRACE;
    private String EXCEPTION_TYPE;
    private String EXCEPTION_TIME;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSTACKTRACE() {
        return STACKTRACE;
    }

    public void setSTACKTRACE(String STACKTRACE) {
        this.STACKTRACE = STACKTRACE;
    }

    public String getEXCEPTION_TYPE() {
        return EXCEPTION_TYPE;
    }

    public void setEXCEPTION_TYPE(String EXCEPTION_TYPE) {
        this.EXCEPTION_TYPE = EXCEPTION_TYPE;
    }

    public String getEXCEPTION_TIME() {
        return EXCEPTION_TIME;
    }

    public void setEXCEPTION_TIME(String EXCEPTION_TIME) {
        this.EXCEPTION_TIME = EXCEPTION_TIME;
    }
}
