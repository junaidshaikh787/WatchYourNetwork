package com.retro.logger.model;

import java.io.Serializable;

public class LogModel implements Serializable {
    String ID;
    String CALLMETHOD;
    String STATUS;
    String URL;
    String REQUEST_HEADER;
    String REQUEST;
    String RESPONSE_HEADER;
    String RESPONSE;
    String RESPONSE_TIME;
    String API_CALL_TIME;

    public String getAPI_CALL_TIME() {
        return API_CALL_TIME;
    }

    public void setAPI_CALL_TIME(String API_CALL_TIME) {
        this.API_CALL_TIME = API_CALL_TIME;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCALLMETHOD() {
        return CALLMETHOD;
    }

    public void setCALLMETHOD(String CALLMETHOD) {
        this.CALLMETHOD = CALLMETHOD;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getREQUEST_HEADER() {
        return REQUEST_HEADER;
    }

    public void setREQUEST_HEADER(String REQUEST_HEADER) {
        this.REQUEST_HEADER = REQUEST_HEADER;
    }

    public String getREQUEST() {
        return REQUEST;
    }

    public void setREQUEST(String REQUEST) {
        this.REQUEST = REQUEST;
    }

    public String getRESPONSE_HEADER() {
        return RESPONSE_HEADER;
    }

    public void setRESPONSE_HEADER(String RESPONSE_HEADER) {
        this.RESPONSE_HEADER = RESPONSE_HEADER;
    }

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    public String getRESPONSE_TIME() {
        return RESPONSE_TIME;
    }

    public void setRESPONSE_TIME(String RESPONSE_TIME) {
        this.RESPONSE_TIME = RESPONSE_TIME;
    }
}
