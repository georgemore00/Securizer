package com.example.AuthenticationServer.twillio;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class TwillioConfiguration {
    @Value("${TWILIO_ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${TWILIO_AUTH_TOKEN}")
    private String AUTH_TOKEN;

    @Value("${TWILLIO_PHONE_NUMBER}")
    private String TWILLIO_PHONE_NUMBER;

    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    public void setACCOUNT_SID(String ACCOUNT_SID) {
        this.ACCOUNT_SID = ACCOUNT_SID;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public void setAUTH_TOKEN(String AUTH_TOKEN) {
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    public String getTWILLIO_PHONE_NUMBER() {
        return TWILLIO_PHONE_NUMBER;
    }

    public void setTWILLIO_PHONE_NUMBER(String TWILLIO_PHONE_NUMBER) {
        this.TWILLIO_PHONE_NUMBER = TWILLIO_PHONE_NUMBER;
    }
}
