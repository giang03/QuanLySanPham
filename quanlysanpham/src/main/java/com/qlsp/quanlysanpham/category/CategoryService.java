package com.qlsp.quanlysanpham.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository cateRepo;

    public Page<Category> listAll(int pageNumber, String keyword, String sortField, String sortDir){
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, 3, sort);
        if(keyword.equals("null"))
            return cateRepo.findAll(pageRequest);
        return cateRepo.findAll(keyword,pageRequest);
    }

    public List<Category> listAll(){
        return cateRepo.findAll();
    }

    public void save(Category category){
        cateRepo.save(category);
    }

    public Category getCategoryById(Integer id){
        return cateRepo.findById(id).get();
    }

    public void deleteCategoryById(Integer id){
        cateRepo.deleteById(id);
    }
}
