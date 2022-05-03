package com.example.BusinessLogicServer.security.providers;

import com.example.BusinessLogicServer.security.proxy.AuthenticationServerProxy;
import com.example.BusinessLogicServer.security.aos.OtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final static Logger logger = Logger.getLogger(OtpAuthenticationProvider.class.getName());

    @Autowired
    private AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("Attempting to authenticate a OTP Authentication..");

        String username = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());

        boolean result = proxy.sendOTP(username,code);

        if(result){
            return new OtpAuthentication(username,code);
        }
        else{
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.isAssignableFrom(OtpAuthentication.class);
    }
}
