package com.pokemon.api.api.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Data
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthEntrypoint authEntrypoint;
    private  CustomUserDetailsService userDetailsService;
@Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService,JwtAuthEntrypoint authEntrypoint) {
        this.userDetailsService = userDetailsService;
        this.authEntrypoint= authEntrypoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                  .exceptionHandling(authEntrypoint)
                  .and()
                .sessionManagements()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  .and()

                .authorizeRequests()
                   .requestMatchers("/api/auth/**").permitAll()
                   .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
@Bean
PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
}
