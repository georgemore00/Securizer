package com.example.AuthenticationServer.twillio;

import com.twilio.Twilio;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class TwillioInitializer {

    private final TwillioConfiguration twillioConfiguration;

    public TwillioInitializer(TwillioConfiguration twillioConfiguration) {
        this.twillioConfiguration = twillioConfiguration;
        Twilio.init(twillioConfiguration.getACCOUNT_SID(),twillioConfiguration.getAUTH_TOKEN());
    }

}
