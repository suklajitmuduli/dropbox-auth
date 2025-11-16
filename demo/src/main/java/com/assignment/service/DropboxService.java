package com.assignment.service;


import com.assignment.config.AppConfig;
import com.assignment.model.AccessTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class DropboxService {

    private final AppConfig config;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private static String accessToken = null;  // STATIC = persists across instance reloads

    public DropboxService(AppConfig config) {
        this.config = config;
    }

    public String generateAuthUrl() {
        return "https://www.dropbox.com/oauth2/authorize"
                + "?client_id=" + config.clientId
                + "&response_type=code"
                + "&redirect_uri=" + config.redirectUri
                + "&token_access_type=offline";
    }

    public String exchangeCodeForToken(String code) throws Exception {

        RequestBody body = new FormBody.Builder()
                .add("code", code)
                .add("grant_type", "authorization_code")
                .add("client_id", config.clientId)
                .add("client_secret", config.clientSecret)
                .add("redirect_uri", config.redirectUri)
                .build();

        Request request = new Request.Builder()
                .url("https://api.dropboxapi.com/oauth2/token")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        System.out.println("TOKEN API RESPONSE = " + json);

        AccessTokenResponse tokenResponse =
                mapper.readValue(json, AccessTokenResponse.class);

        accessToken = tokenResponse.getAccess_token(); // store final token

        return accessToken;
    }

    public String getCurrentToken() {
        return accessToken;
    }

    public String getTeamMembers() throws Exception {

        if (accessToken == null) {
            return "TOKEN IS NULL — Authenticate first using /auth";
        }

        Request request = new Request.Builder()
                .url("https://api.dropboxapi.com/2/team/members/list_v2")
                .post(RequestBody.create("", MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

