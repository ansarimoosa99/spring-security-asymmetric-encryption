package com.ansari.app.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {


    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refershToken;

    @JsonProperty("token_type")
    private String tokenType;
}
