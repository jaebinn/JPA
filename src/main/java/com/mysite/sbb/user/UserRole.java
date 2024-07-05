package com.mysite.sbb.user;

import lombok.Getter;

@Getter
//enum: 열거자료형
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}