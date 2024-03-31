package ma.security.service;

import ma.security.entities.AppUser;

public interface AccountService {
    void addNewUser(String username, String password, String email, String confirmPassword);
    void addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);

}
