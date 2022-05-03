package com.example.AuthenticationServer.twillio;

public interface SmsSender {
    public void sendSms(SmsRequest smsRequest);
}
