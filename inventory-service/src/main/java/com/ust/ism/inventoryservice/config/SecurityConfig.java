package com.ust.ism.inventoryservice.config;

import com.ust.ism.inventoryservice.security.CustomJwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import com.ust.ism.inventoryservice.security.JwtAuthConverter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity
public class SecurityConfig {
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/public/**").permitAll()   // Public APIs (if any)
                    .anyRequest().authenticated()               // Secure everything else
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );

    return httpSecurity.build();
}

    @Bean 
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(new JwtAuthConverter());
        converter.setJwtGrantedAuthoritiesConverter(new CustomJwtAuthenticationConverter());
        return converter;
    }
}
