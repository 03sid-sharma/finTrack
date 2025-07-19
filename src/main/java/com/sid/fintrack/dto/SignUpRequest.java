package com.sid.fintrack.dto;

import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(@NotBlank String email,
                            @NotBlank String password) {
}
