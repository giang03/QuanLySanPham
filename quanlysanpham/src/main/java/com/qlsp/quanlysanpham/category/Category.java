package com.qlsp.quanlysanpham.category;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    private String logo;

    @Override
    public String toString() {
        return this.name;
    }
    @Transient
    public String getLogoImagePath(){
        if(logo == null || id == null) return null;
        return "/category_logo/" + logo;
    }
}
