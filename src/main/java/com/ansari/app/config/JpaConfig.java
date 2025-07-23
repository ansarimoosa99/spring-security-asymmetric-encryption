package com.ansari.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // we can simppy avoid this class and add this annotation on application class file
public class JpaConfig {
}
