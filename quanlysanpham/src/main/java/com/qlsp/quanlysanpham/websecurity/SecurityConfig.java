package com.qlsp.quanlysanpham.websecurity;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.qlsp.quanlysanpham.users.UserDetailServiceImpl;

import lombok.AllArgsConstructor;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailServiceImpl userDetailsServiceImpl;
    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    //@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.withUsername("giang")
                                .password(encoder.encode("giang@1010"))
                                .roles("ADMIN")
                                .build();
        UserDetails user = User.withUsername("giang1")
                                .password(encoder.encode("giang123"))
                                .roles("USER")
                                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/categories/**","/products/edit/**","/products/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
            )
            .exceptionHandling(Customizer -> Customizer
                .accessDeniedPage("/notAuthorized")
            )
            .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .userDetailsService(userDetailsServiceImpl);

        return  http.build();
    }


}
