package com.deepeningInSpringBoot.dto;

import com.deepeningInSpringBoot.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
