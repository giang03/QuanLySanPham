package com.qlsp.quanlysanpham.webconfig;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path categoryUploadDir = Paths.get("./category_logo");
        String categoryUploadPath = categoryUploadDir.toFile().getAbsolutePath();

        Path productUploadDir = Paths.get("./product_logos");
        String productUploadPath = productUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/product_logos/**").addResourceLocations("file:/" + productUploadPath + "/");
        registry.addResourceHandler("/category_logo/**").addResourceLocations("file:/" + categoryUploadPath + "/" );
    }
    
}
