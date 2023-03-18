package project.blog.vo;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("ROLE_USER"),MANAGER("ROLE_MANAGER"),ADMIN("ROLE_ADMIN");

    private final String roleName;

    UserRole(String role) {
        this.roleName =role;
    }

}
