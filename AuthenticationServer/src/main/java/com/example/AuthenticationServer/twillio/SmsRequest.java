package com.example.AuthenticationServer.twillio;

public class SmsRequest {

    private String recipient;
    private String message;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
