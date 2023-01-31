package com.security.entity;

public enum RoleType {
    ROLE_USER("유저"), ROLE_MANAGER("매니저"), ROLE_ADMIN("관리자");

    private final String description;

    RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
