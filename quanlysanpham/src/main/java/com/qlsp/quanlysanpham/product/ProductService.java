package com.qlsp.quanlysanpham.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ProductService {
    @Autowired
    private ProductRepository proRepo;


    public String listProduct(Model model){
        PageRequest pageRequest = PageRequest.of(1, 3); 
        Page<Product> page = proRepo.findAll(pageRequest);
        List<Product> listProducts = page.getContent();
        model.addAttribute("listProducts",listProducts); 
        return "products";
    }
}
