package com.assignment.controller;


import com.assignment.service.DropboxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final DropboxService dropboxService;

    public AuthController(DropboxService dropboxService) {
        this.dropboxService = dropboxService;
    }

    @GetMapping("/auth")
    public String authenticate() {
        return "Click here to login: <br><a href='"
                + dropboxService.generateAuthUrl() + "'>Login with Dropbox</a>";
    }

    @GetMapping("/oauth/callback")
    public String oauthCallback(@RequestParam String code) {

        System.out.println("Received CALLBACK CODE = " + code);

        try {
            String token = dropboxService.exchangeCodeForToken(code);

            System.out.println("TOKEN STORED = " + token);

            return "Authentication Successful! <br>Access Token: <br><b>" + token + "</b>";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error Exchanging Token: " + e.getMessage();
        }
    }

    @GetMapping("/team/members")
    public String getTeamMembers() throws Exception {

        System.out.println("USING TOKEN = " + dropboxService.getCurrentToken());

        return dropboxService.getTeamMembers();
    }
}


