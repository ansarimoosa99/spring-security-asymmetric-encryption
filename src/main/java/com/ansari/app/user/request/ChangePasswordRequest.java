package com.ansari.app.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {

    @NotBlank(message = "VALIDATION.CHANGEPASSWORD.CURRENTPASSWORD.NOT_BLANK")
    @Size(
            min = 8,
            max = 20,
            message = "VALIDATION.CHANGEPASSWORD.CURRENTPASSWORD.SIZE"
    )
    @Pattern(
            regexp = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.\\W).*$",
            message = "VALIDATION.CHANGEPASSWORD.CURRENTPASSWORD.WEAK"
    )
    @Schema(example = "Password@123")
    private String currentPassword;
    @NotBlank(message = "VALIDATION.CHANGEPASSWORD.NEWPASSWORD.NOT_BLANK")
    @Size(
            min = 8,
            max = 20,
            message = "VALIDATION.CHANGEPASSWORD.NEWPASSWORD.SIZE"
    )
    @Pattern(
            regexp = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.\\W).*$",
            message = "VALIDATION.CHANGEPASSWORD.NEWPASSWORD.WEAK"
    )
    @Schema(example = "Password@123")
    private String newPassword;
    @NotBlank(message = "VALIDATION.CHANGEPASSWORD.CONFIRMNEWPASSWORD.NOT_BLANK")
    @Size(
            min = 8,
            max = 20,
            message = "VALIDATION.CHANGEPASSWORD.CONFIRMNEWPASSWORD.SIZE"
    )
    @Pattern(
            regexp = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.\\W).*$",
            message = "VALIDATION.CHANGEPASSWORD.CONFIRMNEWPASSWORD.WEAK"
    )
    @Schema(example = "Password@123")
    private String confirmNewPassword;
}
