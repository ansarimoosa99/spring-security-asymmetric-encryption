package com.ansari.app.auth.impl;

import com.ansari.app.auth.AuthenticationService;
import com.ansari.app.auth.request.AuthenticationRequest;
import com.ansari.app.auth.request.RefreshRequest;
import com.ansari.app.auth.request.RegistrationRequest;
import com.ansari.app.auth.response.AuthenticationResponse;
import com.ansari.app.exception.BusinessException;
import com.ansari.app.exception.ErrorCode;
import com.ansari.app.role.RoleRepository;
import com.ansari.app.security.JwtService;
import com.ansari.app.user.User;
import com.ansari.app.user.UserMapper;
import com.ansari.app.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


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


    }


    @Override
    public AuthenticationResponse refresh(RefreshRequest request) {
        return null;
    }


    private void checkUserEmail(final String email) {
        final boolean emialExists = this.userRepository.existsByEmailIgnoreCase(email);
        if(emialExists){
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkUserPhoneNumber(final String phoneNumber) {
        final boolean phoneNumberExists = this.userRepository.existsByPhoneNumber(phoneNumber);
        if(phoneNumberExists){
            throw new BusinessException(ErrorCode.PHONE_ALREADY_EXISTS);
        }
    }

    private void checkPassword(final String password, final String confirmPassword) {
        if(password != null && password.equals(confirmPassword)){
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }

    }

}
