package ma.security.service;

import lombok.AllArgsConstructor;
import ma.security.entities.AppRole;
import ma.security.entities.AppUser;
import ma.security.repos.AppRoleRepo;
import ma.security.repos.AppUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepo appUserRepo;
    private AppRoleRepo appRoleRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public void addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser = appUserRepo.findByUsername(username);
        if (appUser != null) {
            throw new RuntimeException("User already exists");
        }
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Please confirm your password");
        }
        appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        appUserRepo.save(appUser);
    }

    @Override
    public void addNewRole(String role) {
        AppRole appRole = appRoleRepo.findById(role).orElse(null);
        if (appRole != null) {
            throw new RuntimeException("Role already exists");
        }
        appRole = AppRole.builder().roleName(role).build();
        appRoleRepo.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepo.findByUsername(username);
        AppRole appRole = appRoleRepo.findById(role).get();
        appUser.getRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepo.findByUsername(username);
        AppRole appRole = appRoleRepo.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepo.findByUsername(username);
    }
}
