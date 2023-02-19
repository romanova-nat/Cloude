package com.example.clouddiplom.Service;

import com.example.clouddiplom.DTO.FileDTO;
import com.example.clouddiplom.DTO.PersonDTO;
import com.example.clouddiplom.Security.JwtTokenUtil;
import com.example.clouddiplom.Repository.AuthenticationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {

    private AuthenticationRepository authenticationRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private PersonDetailsService personDetailsService;

    public PersonDTO login(FileDTO fileDTO) {
        final String username = fileDTO.getLogin();
        final String password = fileDTO.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = personDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        authenticationRepository.putTokenAndUsername(token, username);
        log.info("User {} authentication. JWT: {}", username, token);
        return new PersonDTO(token);
    }

    public void logout(String authToken) {
        final String token = authToken.substring(7);
        final String username = authenticationRepository.getUsernameByToken(token);
        log.info("User {} logout. JWT is disabled.", username);
        authenticationRepository.removeTokenAndUsernameByToken(token);
    }
}