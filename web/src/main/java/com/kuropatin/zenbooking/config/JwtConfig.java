package com.kuropatin.zenbooking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class JwtConfig {

    @Value("${jwt.config.secret}")
    private String secret;

    @Value("${jwt.config.expiration-time-in-minutes}")
    private Integer expiration; //expiration time in minutes
}