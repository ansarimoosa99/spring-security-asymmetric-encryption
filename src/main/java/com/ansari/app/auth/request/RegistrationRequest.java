package com.ansari.app.auth.request;

import com.ansari.app.validation.NonDisposableEmail;
import com.ansari.app.validation.NotDisposableEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {

    @NotBlank(message = "VALIDATION.REGISTRATION.FIRSTNAME.NOT_BLANK")
    @Size(
            min = 5,
            max = 50,
            message = "VALIDATION.REGISTRATION.FIRSTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$"
            , message = "VALIDATION.REGISTRATION.FIRSTNAME.PATTERN"
    )
    @Schema(example = "Moosa")
    private String firstName;

    @NotBlank(message = "VALIDATION.REGISTRATION.LASTNAME.NOT_BLANK")
    @Size(
            min = 5,
            max = 50,
            message = "VALIDATION.REGISTRATION.LASTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$"
            , message = "VALIDATION.REGISTRATION.LASTNAME.PATTERN"
    )
    @Schema(example = "Ansari")
    private String lastName;
    @NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.NOT_BLANK")
    @Email(message = "VALIDATION.REGISTRATION.EMAIL.FORMAT")
    @NonDisposableEmail(message = "VALIDATION.REGISTRATION.EMAIL.DISPOSABLE")
    @Schema(example = "abc@mail.com")
    private String email;

    @NotBlank(message = "VALIDATION.REGISTRATION.PHONE_NUMBER.NOT_BLANK")
    @Pattern(
            regexp = "^\\+?[0-9]{10,13}$",
            message = "VALIDATION.REGISTRATION.PHONE_NUMBER.PATTERN"
    )
    @Schema(example = "+1234567890")
    private String phoneNumber;
    @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
    @Size(
            min = 8,
            max = 20,
            message = "VALIDATION.REGISTRATION.PASSWORD.SIZE"
    )
    @Pattern(
            regexp = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.\\W).*$",
            message = "VALIDATION.REGISTRATION.PASSWORD.WEAK"
    )
    @Schema(example = "<PASSWORD>")
    private String password;
    @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
    @Size(
            min = 8,
            max = 20,
            message = "VALIDATION.REGISTRATION.PASSWORD.SIZE"
    )
    @Schema(example = "<PASSWORD>")
    private String confirmPassword;

}
