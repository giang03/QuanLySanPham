package com.qlsp.quanlysanpham;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuanlysanphamController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
