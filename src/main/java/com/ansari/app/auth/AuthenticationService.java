package com.ansari.app.auth;

import com.ansari.app.auth.request.AuthenticationRequest;
import com.ansari.app.auth.request.RefreshRequest;
import com.ansari.app.auth.request.RegistrationRequest;
import com.ansari.app.auth.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void register(RegistrationRequest request);

    AuthenticationResponse refresh(RefreshRequest request);
}
