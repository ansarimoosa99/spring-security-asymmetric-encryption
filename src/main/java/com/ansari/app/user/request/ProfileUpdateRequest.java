package com.ansari.app.user.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateRequest {
    @NotBlank(message = "VALIDATION.PROFILEUPDATE.FIRSTNAME.NOT_BLANK")
    @Size(
            min = 5,
            max = 50,
            message = "VALIDATION.PROFILEUPDATE.FIRSTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$"
            , message = "VALIDATION.PROFILEUPDATE.FIRSTNAME.PATTERN"
    )
    @Schema(example = "Moosa")
    private String firstName;
    @NotBlank(message = "VALIDATION.PROFILEUPDATE.LASTNAME.NOT_BLANK")
    @Size(
            min = 5,
            max = 50,
            message = "VALIDATION.PROFILEUPDATE.LASTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$"
            , message = "VALIDATION.PROFILEUPDATE.LASTNAME.PATTERN"
    )
    @Schema(example = "Ansari")
    private String lastName;
    @NotBlank(message = "VALIDATION.PROFILEUPDATE.DATEOFBIRTH.NOT_BLANK")
    @Schema(example = "01-01-2000")
    @Pattern(
            regexp = "^\\d{2}-\\d{2}-\\d{4}$",
            message = "VALIDATION.PROFILEUPDATE.DATEOFBIRTH.PATTERN"
    )
    private LocalDate dateOfBirth;

}
