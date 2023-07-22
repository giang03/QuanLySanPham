package com.qlsp.quanlysanpham.users;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class AppUser {
    @Id
    private String userId;

    @Column(nullable = false, unique = true)    
    private String username;    

    @Column(nullable = false)
    private String password;    
    
    @Column(nullable = false, unique = true)    
    private String email;    

    @ManyToMany(fetch = FetchType.EAGER)
    List<AppRole> roles;

    
}
