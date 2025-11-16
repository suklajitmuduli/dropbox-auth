package com.assignment.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("scope")
    private String scope;

    public String getAccess_token() { return accessToken; }
    public void setAccess_token(String accessToken) { this.accessToken = accessToken; }

    public String getRefresh_token() { return refreshToken; }
    public void setRefresh_token(String refreshToken) { this.refreshToken = refreshToken; }

    public String getToken_type() { return tokenType; }
    public void setToken_type(String tokenType) { this.tokenType = tokenType; }

    public Long getExpiresIn() { return expiresIn; }
}

