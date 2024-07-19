package com.testing.questions_history.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class EndToEndSecurityDemo {

    private static final String[] SECURED_URLs = {"/books/**"};

    private static final String[] UN_SECURED_URLs = {
            "/",
            "/login",
            "/error",
            "/registration/**",
            "/webjars/**",
            "/images/**",
            "/css/**",
            "/fonts/**",
            "/js/**",
            "/icons/**",
            "/home/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/", "/login", "/error","/registration/**", "/webjars/**", "/images/**").permitAll()
                        .requestMatchers(UN_SECURED_URLs).permitAll()
                        // .requestMatchers(SECURED_URLs).hasAnyAuthority("USER", "ADMIN"))
                        // .requestMatchers(SECURED_URLs).hasAuthority("ADMIN"))
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/")
                        .invalidateHttpSession(true).clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
