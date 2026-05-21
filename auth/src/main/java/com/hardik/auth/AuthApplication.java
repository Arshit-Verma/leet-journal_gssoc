package com.hardik.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@SpringBootApplication
public class AuthApplication {

    private static final Logger logger = LoggerFactory.getLogger(AuthApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> authSuccess() {
        return (auth) -> {
            var res = auth.getAuthentication();
            logger.info("Logged in as: {} Type: {}", res.getName(), res.getClass().getSimpleName());
        };
    }

}
