package com.qlsp.quanlysanpham;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.qlsp.quanlysanpham.category.Category;
import com.qlsp.quanlysanpham.category.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestsCategoryRepository {
    @Autowired
    private CategoryRepository repo;

    @Autowired
    private TestEntityManager manager;

    // @Test
    // public void testAddCategory(){
    //     Category category = new Category("Sports");
    //     repo.save(category);
    // }
    // @Test
    // public void testAddMoreCategory(){
    //     Category cate = new Category("Electronic Device");
    //     Category cate1 = new Category("Men's Fashion");
    //     repo.saveAll(List.of(cate, cate1));
    // }

    @Test
    public void removeCategoryById(){
        repo.deleteById(2);
    }

    @Test
    public void readCategoryById(){
        Category cate = manager.find(Category.class, 2);
        System.out.println(cate);
    }
}
