package com.sak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sak.entity.Product;
import com.sak.repository.ProductRepository;
import com.sak.repository.UserRepository;
import com.sak.service.ProductService;

import jakarta.servlet.annotation.MultipartConfig;

@Controller
@MultipartConfig
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin_dash")
    public ModelAndView adminDash() {
        return new ModelAndView("admin") ;
    }

    @GetMapping("/user_list")
    public ModelAndView getuserList() {
        ModelAndView model = new ModelAndView("user_list");
        model.addObject("users", userRepository.findAll());
        return model;
    }

    @PostMapping("/delete_user/{id}")
    public ModelAndView deleteuserList(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/admin/user_list");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/add_product")
    public ModelAndView showAddProductForm(Model model) {
        model.addAttribute("product", new Product()); 
        return new ModelAndView("add_product"); 
    }

    @PostMapping("/add_product")
    public ModelAndView addProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile imageFile) {
       return  productService.saveProduct(product, imageFile);
    }

    @GetMapping("/get_product")
    public String getProduct(Model model) {
        productService.getProductAdmin(model);
        return "get_product";
    }
    
    @PostMapping("/delete_product/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return new ModelAndView("redirect:/admin/get_product");
    }
}
