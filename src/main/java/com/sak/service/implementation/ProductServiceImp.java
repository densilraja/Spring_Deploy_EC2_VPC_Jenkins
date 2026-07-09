package com.sak.service.implementation;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sak.dto.ProductDto;
import com.sak.entity.Product;
import com.sak.repository.ProductRepository;
import com.sak.service.ProductService;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ModelAndView saveProduct(Product product, MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                byte[] imageBytes = new byte[imageFile.getInputStream().available()];
                imageFile.getInputStream().read(imageBytes);
                product.setProductImage(imageBytes);
            }
            productRepository.save(product);
            return new ModelAndView("redirect:/admin/add_product").addObject("success", "Product added successfully");
            
        } catch (IOException e) {
            e.printStackTrace();
            return new ModelAndView("add_product").addObject("error", "Error uploading image");
        }
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void getProductHome(Model model) {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos = products.stream().map(product -> {
        ProductDto productDto = new ProductDto();
            productDto.setProductName(product.getProductName());
            productDto.setProductDescription(product.getProductDescription());
            productDto.setPrice(product.getPrice());
            if (product.getProductImage() != null) {
                String base64Image = Base64.getEncoder().encodeToString(product.getProductImage());
                productDto.setBase64Image(base64Image); 
            }
            return productDto;
        }).collect(Collectors.toList());
        model.addAttribute("products", productDtos);
    }

    @Override
    public void getProductAdmin(Model model) {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos = products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
                productDto.setProductId(product.getProductId());
                productDto.setProductName(product.getProductName());
                productDto.setProductDescription(product.getProductDescription());
                productDto.setPrice(product.getPrice());
                productDto.setQuantity(product.getQuantity());
                if (product.getProductImage() != null) {
                    String base64Image = Base64.getEncoder().encodeToString(product.getProductImage());
                    productDto.setBase64Image(base64Image); 
                }
                return productDto;
            }).collect(Collectors.toList());
            model.addAttribute("products", productDtos);
    }
            
}

