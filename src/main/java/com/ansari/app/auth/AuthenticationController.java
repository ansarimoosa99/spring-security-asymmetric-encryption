package com.ansari.app.auth;

import com.ansari.app.auth.request.AuthenticationRequest;
import com.ansari.app.auth.request.RefreshRequest;
import com.ansari.app.auth.request.RegistrationRequest;
import com.ansari.app.auth.response.AuthenticationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid
            @RequestBody
            final AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid
            @RequestBody
            final RegistrationRequest request) {
        this.authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();   //CREATED 201
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            @Valid
            @RequestBody
            final RefreshRequest request) {
        return ResponseEntity.ok(authenticationService.refresh(request));
    }

}
