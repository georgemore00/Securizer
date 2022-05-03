package com.example.AuthenticationServer.services;

import com.example.AuthenticationServer.models.Otp;
import com.example.AuthenticationServer.models.User;
import com.example.AuthenticationServer.repositories.OtpRepository;
import com.example.AuthenticationServer.repositories.UserRepository;
import com.example.AuthenticationServer.twillio.SmsRequest;
import com.example.AuthenticationServer.twillio.SmsSender;
import com.example.AuthenticationServer.util.GenerateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Value("${USER_PHONE_NUMBER}")
    private String USER_PHONE_NUMBER;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    @Qualifier("twillio")
    private SmsSender smsSender;

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user){
        Optional<User> u = userRepository.findUserByUsername(user.getUsername());
        
        if(u.isPresent()){
            User systemUser = u.get();
            if(passwordEncoder.matches(user.getPassword(),systemUser.getPassword())){
                renewOtp(systemUser);
            }
            else{
                throw new BadCredentialsException("Bad credentials");
            }
        } else
        {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private void renewOtp(User systemUser) {
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(systemUser.getUsername());
        String code = GenerateCodeUtil.generateCode();

        if(userOtp.isPresent()){
            Otp systemOtp = userOtp.get();
            systemOtp.setCode(code);
        } else{
            Otp firstOtp = new Otp();
            firstOtp.setUsername(systemUser.getUsername());
            firstOtp.setCode(code);
            otpRepository.save(firstOtp);
        }
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setRecipient(USER_PHONE_NUMBER);
        smsRequest.setMessage(code);
        smsSender.sendSms(smsRequest);
    }

    public boolean check(Otp otpToValidate){
        Optional<Otp> o = otpRepository.findOtpByUsername(otpToValidate.getUsername());
        if(o.isPresent()){
            Otp systemOtp = o.get();
            return systemOtp.getCode().equals(otpToValidate.getCode());
        }
        else{
            return false;
        }
    }
}
