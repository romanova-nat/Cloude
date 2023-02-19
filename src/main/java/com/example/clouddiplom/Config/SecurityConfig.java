package com.example.clouddiplom.Config;

import com.example.clouddiplom.Security.JwtAuthenticationEntryPoint;
import com.example.clouddiplom.Security.JwtRequestFilter;
import com.example.clouddiplom.Service.PersonDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig implements WebSecurityConfigurer {

    private final PersonDetailsService personDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Value("${cors.credentials}")
    private Boolean credentials;

    @Value("${cors.origins}")
    private String origins;

    @Value("${cors.methods}")
    private String methods;

    @Value("${cors.headers}")
    private String headers;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(credentials);
        configuration.setAllowedOrigins(List.of(origins));
        configuration.setAllowedMethods(List.of(methods));
        configuration.setAllowedHeaders(List.of(headers));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManagern() throws Exception {
//        return super.authenticationManager();
//    }


    //   protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        httpSecurity.cors();
//        httpSecurity.authorizeRequests().requestMatchers("/h2-console/**").permitAll();
//        httpSecurity.csrf().disable();
//        httpSecurity.headers().frameOptions().disable();
//
//        httpSecurity
//                .authorizeRequests()
//                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .and()
//                .authorizeRequests().requestMatchers("/login").permitAll();
//
//        httpSecurity
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }

    @Override
    public void init(SecurityBuilder builder) throws Exception {
    }

    @Override
    public void configure(SecurityBuilder builder) throws Exception {
        HttpSecurity httpSecurity = null;
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.cors();
        httpSecurity.authorizeRequests().requestMatchers("/h2-console/**").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        httpSecurity
                .authorizeRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and()
                .authorizeRequests().requestMatchers("/login").permitAll();

        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}

