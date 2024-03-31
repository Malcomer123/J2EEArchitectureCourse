package ma.security;

import lombok.AllArgsConstructor;
import ma.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Le cas de JdbcUserDetailsManager
     */

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }


    /**
     * Le cas de InMEmoUserDetailsManager
     */

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
//                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
//                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
//
//        );
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.formLogin(lg -> lg.loginPage("/login").permitAll().defaultSuccessUrl("/"))
                .rememberMe(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar.requestMatchers("/webjars/**").permitAll())
                .authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .userDetailsService(userDetailsService)
                .exceptionHandling(e->e.accessDeniedPage("/403"))
                .build();
    }

}
