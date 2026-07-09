package com.sak.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sak.entity.Product;

public interface ProductService {

    ModelAndView saveProduct(Product product, MultipartFile imageFile);

    Product getProductById(Long id);

    void getProductHome(Model model);

    void getProductAdmin(Model model);
}
