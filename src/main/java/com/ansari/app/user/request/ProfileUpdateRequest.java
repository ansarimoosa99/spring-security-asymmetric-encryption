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
    @Size(
            min = 1,
            max = 50,
            message = "VALIDATION.PROFILEUPDATE.FIRSTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$"
            , message = "VALIDATION.PROFILEUPDATE.FIRSTNAME.PATTERN"
    )
    @Schema(example = "Moosa")
    private String firstName;

    @Size(
            min = 1,
            max = 50,
            message = "VALIDATION.PROFILEUPDATE.LASTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$"
            , message = "VALIDATION.PROFILEUPDATE.LASTNAME.PATTERN"
    )
    @Schema(example = "Ansari")
    private String lastName;


//    @Pattern(
//            regexp = "^\\d{4}-\\d{2}-\\d{4}$",
//            message = "VALIDATION.PROFILEUPDATE.DATEOFBIRTH.PATTERN"
//    )
    @Schema(example = "2000-01-24")
    private LocalDate dateOfBirth;

}
