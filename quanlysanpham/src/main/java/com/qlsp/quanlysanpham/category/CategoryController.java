package com.qlsp.quanlysanpham.category;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public String list(Model model) {
        return pageCategory(model, 1,"null","name","asc");
    }

    @GetMapping("/categories/page/{pageNumber}")
    public String pageCategory(Model model ,
                            @PathVariable int pageNumber,
                            @Param("keyword") String keyword,
                            @Param("sortField") String sortField,
                            @Param("sortDir") String sortDir){
        Page<Category> page = service.listAll(pageNumber - 1, keyword,sortField,sortDir);
        List<Category> categories = page.getContent();
        if(categories.isEmpty()){
            return "index";
        }
        int totalPages = page.getTotalPages();
        long totalElements =  page.getTotalElements();
        model.addAttribute("categories", categories);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        return "categories";
    }
    
    @GetMapping("/categories/new")
    public String newCatelory(Model model) {
        model.addAttribute("category", new Category());
        return "category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category, 
                            @RequestParam("fileImage") MultipartFile multipartFile) throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        category.setLogo(fileName);
        service.save(category);

        String uploadDir = "./category_logo";
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new IOException("Could not save");
        }
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCate(@PathVariable Integer id, Model model){
        Category category = service.getCategoryById(id);
        model.addAttribute("category", category);
        return "category_form";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCate(@PathVariable Integer id){
        service.deleteCategoryById(id);
        return "redirect:/categories";
    }
}
