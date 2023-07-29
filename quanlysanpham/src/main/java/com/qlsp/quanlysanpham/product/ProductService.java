package com.qlsp.quanlysanpham.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository proRepo;

    public Page<Product> listAll(int pageNumber,String keyword, String sortField, String sortDir){
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, 2, sort);
        if(keyword.equals("null"))
            return proRepo.findAll(pageRequest);
        return proRepo.findAll(keyword,pageRequest);
    }

    public Product save(Product product){
        return proRepo.save(product);
    }

    public void deleteProductById(int id){
        proRepo.deleteById(id);
    }

    public Product findProductById(int id){
        return proRepo.findById(id).get();
    }
}
