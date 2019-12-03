package com.mia.eatgo.application;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordWrongException extends RuntimeException {
    PasswordWrongException(){
        super("Password is Wrong");
    }

}
