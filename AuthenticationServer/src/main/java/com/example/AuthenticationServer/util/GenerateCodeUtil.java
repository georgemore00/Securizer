package com.example.AuthenticationServer.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class GenerateCodeUtil {

    public static String generateCode() {
        String code;

        try{
            SecureRandom randomGenerator = SecureRandom.getInstanceStrong();
            int randomValue = randomGenerator.nextInt(9000) + 1000;
            code = String.valueOf(randomValue);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code.");
        }

        return code;
    }
}
