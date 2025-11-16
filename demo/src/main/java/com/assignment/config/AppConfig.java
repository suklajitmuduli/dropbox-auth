package com.assignment.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${dropbox.client.id}")
    public String clientId;

    @Value("${dropbox.client.secret}")
    public String clientSecret;

    @Value("${dropbox.redirect.uri}")
    public String redirectUri;

}
