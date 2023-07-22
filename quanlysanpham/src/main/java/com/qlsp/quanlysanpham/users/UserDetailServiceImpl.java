package com.qlsp.quanlysanpham.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException(String.format("User %s not found", username));

        //String[] roles = appUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);
        List<SimpleGrantedAuthority> authorities  = appUser.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
        UserDetails userDetails = User
                                    .withUsername(appUser.getUsername())
                                    .password(appUser.getPassword())
                                    //.roles(roles)
                                    .authorities(authorities)
                                    .build();
        return userDetails;
    }   
    
}
