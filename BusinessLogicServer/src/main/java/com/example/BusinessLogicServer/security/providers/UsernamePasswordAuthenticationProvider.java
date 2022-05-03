package com.example.BusinessLogicServer.security.providers;

import com.example.BusinessLogicServer.security.proxy.AuthenticationServerProxy;
import com.example.BusinessLogicServer.security.aos.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final static Logger logger = Logger.getLogger(UsernamePasswordAuthenticationProvider.class.getName());

    @Autowired
    private AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("Attempting to authenticate a UsernamePasswordAuthentication..");
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        proxy.sendAuth(username,password);
        return new UsernamePasswordAuthentication(username,password);
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.isAssignableFrom(UsernamePasswordAuthentication.class);
    }
}
