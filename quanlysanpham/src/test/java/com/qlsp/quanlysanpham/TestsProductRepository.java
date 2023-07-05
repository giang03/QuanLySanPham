package com.qlsp.quanlysanpham;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.qlsp.quanlysanpham.category.Category;
import com.qlsp.quanlysanpham.product.Product;
import com.qlsp.quanlysanpham.product.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestsProductRepository {
    @Autowired
    private ProductRepository repo;

    @Autowired
    private TestEntityManager manager;

    @Test
    public void addOneProduct(){
        Category category = manager.find(Category.class, 3);
        Product product = new Product("Pants", category);
        repo.save(product);
    }

    @Test
    public void removeProductById(){
        repo.deleteById(1);
    }

    @Test
    public void readProductById(){
        Product product = manager.find(Product.class, 2);
        System.out.println(product);
    }
}
