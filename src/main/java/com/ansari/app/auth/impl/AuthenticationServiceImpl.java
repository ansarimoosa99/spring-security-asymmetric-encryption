package com.ansari.app.auth.impl;

import com.ansari.app.auth.AuthenticationService;
import com.ansari.app.auth.request.AuthenticationRequest;
import com.ansari.app.auth.request.RefreshRequest;
import com.ansari.app.auth.request.RegistrationRequest;
import com.ansari.app.auth.response.AuthenticationResponse;
import com.ansari.app.exception.BusinessException;
import com.ansari.app.exception.ErrorCode;
import com.ansari.app.role.Role;
import com.ansari.app.role.RoleRepository;
import com.ansari.app.security.JwtService;
import com.ansari.app.user.User;
import com.ansari.app.user.UserMapper;
import com.ansari.app.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        final Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );  //will make sure that the user exists and the password is correct [ user is correctly authenticated ] or else will throw an exception

        final User user = (User) auth.getPrincipal();  // get the authenticated user from the authentication object
        // user implements UserDetails, so we can cast it to User

        final String token = this.jwtService.generateAccessToken(user.getUsername());  // generate JWT token for the authenticated user
        final String refreshToken = this.jwtService.generateRefreshToken(user.getUsername());  // generate refresh token for the authenticated user

        final String tokenType = "Bearer";  // token type is Bearer


        return AuthenticationResponse.builder()
                .accessToken(token)
                .refershToken(refreshToken)
                .tokenType(tokenType)
                .build();
    }

    @Override
    @Transactional
    public void register(RegistrationRequest request) {
        checkUserEmail(request.getEmail());
        checkUserPhoneNumber(request.getPhoneNumber());
        checkPassword(request.getPassword(), request.getConfirmPassword());

        //fetch the user role from the database
        final Role userRole = this.roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException("Role user does not exist"));

        final List<Role> roles = new ArrayList<>();  // we have many roles, so we need to create a list of roles
        roles.add(userRole);

        final User user = this.userMapper.toUser(request);  // map the request to user entity
        user.setRoles(roles);
        log.debug("Saving user {}", user);
        this.userRepository.save(user);  // save the user to the database

        //this should be enouughh-----

        //what we need to do afterward is to save or persist the role  itself,  ////but since we are using CascadeType.PERSIST and CascadeType.MERGE in the User entity, it will automatically save the roles when we save the user.
        // we need to assisgn user to the user role as we have many to many relationship
        // not needed[as in Roles we have manytoMany cascade.persist/merge] but still explained in case we dont have cascade.persist/merge
        //how you need to deal w that.
        final List<User> users = new ArrayList<>();
        users.add(user);
        userRole.setUsers(users);
        this.roleRepository.save(userRole);
        // asssign user to user role in case cascading not use;; this will save the user role and assign the user to the user role

    }


    @Override
    public AuthenticationResponse refresh(RefreshRequest request) {
        final String newAccessToken = this.jwtService.refreshToken(request.getRefreshToken());
        final String TokenType = "Bearer";

        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refershToken(request.getRefreshToken())
                .tokenType(TokenType)
                .build();
    }


    private void checkUserEmail(final String email) {
        final boolean emialExists = this.userRepository.existsByEmailIgnoreCase(email);
        if (emialExists) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkUserPhoneNumber(final String phoneNumber) {
        final boolean phoneNumberExists = this.userRepository.existsByPhoneNumber(phoneNumber);
        if (phoneNumberExists) {
            throw new BusinessException(ErrorCode.PHONE_ALREADY_EXISTS);
        }
    }

    private void checkPassword(final String password, final String confirmPassword) {
        if (password != null && password.equals(confirmPassword)) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }

    }

}
