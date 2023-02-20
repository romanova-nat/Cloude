package com.example.clouddiplom.Controller;

import com.example.clouddiplom.Constant.Constant;
import com.example.clouddiplom.DTO.FileDTO;
import com.example.clouddiplom.DTO.PersonDTO;
import com.example.clouddiplom.Service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public PersonDTO login(@RequestBody FileDTO fileDTO) {
        return authenticationService.login(fileDTO);
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("auth-token") String authToken) {
        authenticationService.logout(authToken);
        return Constant.SUCCESS_LOGOUT;
    }
}