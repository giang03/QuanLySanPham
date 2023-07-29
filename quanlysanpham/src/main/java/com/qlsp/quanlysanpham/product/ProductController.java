package com.qlsp.quanlysanpham.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qlsp.quanlysanpham.category.Category;
import com.qlsp.quanlysanpham.category.CategoryService;

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

@Controller
public class ProductController {
    @Autowired
    private ProductService service;
    
    @Autowired
    private CategoryService cateService;
    
    @GetMapping("/products")
    public String list(Model model){
        return pageProduct(model,1, "null", "id", "asc");
    }

    @GetMapping("/products/page/{pageNumber}")
    public String pageProduct(Model model,
                            @PathVariable int pageNumber,
                            @Param("keyword") String keyword,
                            @Param("sortField") String sortField,
                            @Param("sortDir") String sortDir){
        Page<Product> page = service.listAll(pageNumber - 1, keyword, sortField,sortDir);
        List<Product> listProducts = page.getContent();

        int totalPages = page.getTotalPages();
        long totalElements =  page.getTotalElements();

        model.addAttribute("listProducts",listProducts);
        model.addAttribute("keyword",keyword);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);

        
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        return "products";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model){
        List<Category> listCategories =  cateService.listAll();

        model.addAttribute("product", new Product());
        model.addAttribute("listCategories", listCategories);
        return "product_form";
    }

    @PostMapping("/products/save")
    public String save(Product product, 
                        @RequestParam("fileImage") MultipartFile multipartFile) throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setLogo(fileName);
        Product saveProduct = service.save(product);

        String uploadDir = "./product_logos/" + saveProduct.getId();
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath ,StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new IOException("Could not save");
        }

        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String delete(@PathVariable Integer id){
        service.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        List<Category> listCategories =  cateService.listAll();
        
        Product product = service.findProductById(id);
        model.addAttribute("product",product);
        model.addAttribute("listCategories", listCategories);
        return "product_form";
    }
}
