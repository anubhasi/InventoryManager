package com.ust.ism.inventoryservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new HashSet<>();

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> roles = (List<String>) realmAccess.get("roles");

            for (String role : roles) {
                System.out.println("Role - "+role);
                // Map roles to custom authorities
                switch (role) {
                    case "INV_VIEWER": // "ROLE_INV_VIEWER":
                        authorities.add(new SimpleGrantedAuthority("READ_PRIVILEGE"));
                        break;
                    case "INV_MANAGER": //"ROLE_INV_MANAGER":
                        authorities.add(new SimpleGrantedAuthority("READ_PRIVILEGE"));
                        authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGE"));
                        break;
                    case "INV_ADMIN": //"ROLE_INV_ADMIN":
                        authorities.add(new SimpleGrantedAuthority("READ_PRIVILEGE"));
                        authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGE"));
                        authorities.add(new SimpleGrantedAuthority("DELETE_PRIVILEGE"));
                        break;
                    default:
                        // You can also add the raw ROLE_xx as fallback if you want
                        authorities.add(new SimpleGrantedAuthority(role));
                        break;
                }
            }
        }

        return authorities;
    }
}