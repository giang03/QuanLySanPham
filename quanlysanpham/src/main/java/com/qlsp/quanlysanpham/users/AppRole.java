package com.qlsp.quanlysanpham.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class AppRole {

    @Id
    private String role;

    @Override
    public String toString(){
        return role;
    }
}
