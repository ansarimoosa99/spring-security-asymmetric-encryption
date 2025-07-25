package com.ansari.app;

import com.ansari.app.role.Role;
import com.ansari.app.role.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
//@EnableJpaAuditing //used config way
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//quick fix for role not mapped error from registration AuthServiceImpl
	@Bean
	public CommandLineRunner commandLineRunner(final RoleRepository roleRepository){
		return args -> {
			final Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
			if(userRole.isEmpty()){
				final Role role = new Role();
				role.setName("ROLE_USER");
				role.setCreatedBy("APP");
				roleRepository.save(role);
			}
		};
	}

}
