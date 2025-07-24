package com.ansari.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new ApplicationAuditorAware();
    }

    // now we have our auditorAware configured,
    // everytime user tries to create or update an entity[perform an action]
    // we have an entity that extends our base entity, spring will do the rest
    // it will automatically set the createdBy and lastModifiedBy fields
    // it will also set the createdDate and lastModifiedDate fields in BaseEntity class
    // so it will work for all opern, update, delete, and create


}
