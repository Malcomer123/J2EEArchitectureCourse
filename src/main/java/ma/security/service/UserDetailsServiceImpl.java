package ma.security.service;

import lombok.AllArgsConstructor;
import ma.security.entities.AppRole;
import ma.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException("Invalid user"+username);
        String[] roles = appUser.getRoles().stream().map(AppRole::getRoleName).toArray(String[]::new);
        System.out.println(appUser.getUsername() + " " + appUser.getPassword() + " " + appUser.getRoles());
        return User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(roles)
                .build();
    }
}
