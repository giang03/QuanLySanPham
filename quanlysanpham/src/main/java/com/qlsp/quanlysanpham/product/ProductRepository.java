package com.qlsp.quanlysanpham.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p JOIN p.category c WHERE " +
            "CONCAT(p.id, ' ', p.name, ' ', c.name) "+
            "LIKE %?1%")
    public Page<Product> findAll(String keyword,Pageable pageable);
}
