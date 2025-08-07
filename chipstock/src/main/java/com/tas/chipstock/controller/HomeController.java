package com.tas.chipstock.controller;

import com.tas.chipstock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featuredProducts", productService.getAllProducts());
        model.addAttribute("categories", productService.getAllCategories());
        return "home";
    }
}
