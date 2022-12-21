package com.example.selabboard.model.entity;

public enum MemberRole {
    ADMIN(0),
    USER(1);

    private final int role;

    MemberRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
}
