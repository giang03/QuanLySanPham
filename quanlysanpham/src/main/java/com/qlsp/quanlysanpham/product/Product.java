package com.qlsp.quanlysanpham.product;

import org.springframework.data.annotation.Transient;

import com.qlsp.quanlysanpham.category.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor 
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @Column(length = 45, nullable =  true)
    private String logo;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    public String getLogoImagePath(){
        if(logo == null || id == null) return null;
        return "/product_logos/" + id + "/" + logo;
    }
}
