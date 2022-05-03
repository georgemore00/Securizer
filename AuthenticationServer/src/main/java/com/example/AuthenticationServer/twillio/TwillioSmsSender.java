package com.example.AuthenticationServer.twillio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service("twillio")
public class TwillioSmsSender implements SmsSender{

   private final TwillioConfiguration twillioConfiguration;

   private final String from;

    public TwillioSmsSender(TwillioConfiguration twillioConfiguration) {
        this.twillioConfiguration = twillioConfiguration;
        this.from = twillioConfiguration.getTWILLIO_PHONE_NUMBER();
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        String recipient = smsRequest.getRecipient();
        String from = twillioConfiguration.getTWILLIO_PHONE_NUMBER();

        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(recipient),
                        new com.twilio.type.PhoneNumber(from),
                        "Your verification code: " + smsRequest.getMessage())
                .create();

    }
}
