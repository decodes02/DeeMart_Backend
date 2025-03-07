package com.decodes.eCom.Controller;

import com.decodes.eCom.Model.Product;
import com.decodes.eCom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/")
    public String home(){
        return "Welcome to the eCom API";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return service.getProduct(id);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product){
        return service.addProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProductById(@PathVariable int id, @RequestBody Product product){
        Product existingProduct = service.getProduct(id);
        existingProduct.setName(product.getName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setAvailable(product.isAvailable());
        existingProduct.setDate(product.getDate());
        return service.addProduct(existingProduct);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

}
