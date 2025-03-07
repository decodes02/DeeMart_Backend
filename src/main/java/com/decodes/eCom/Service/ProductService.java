package com.decodes.eCom.Service;

import com.decodes.eCom.Model.Product;
import com.decodes.eCom.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getProduct(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }


    public String deleteProduct(int id) {
        return repo.findById(id).map(product -> {
            repo.delete(product);
            return "Deleted Successfully!";
        }).orElse("Product not found!");
    }
}
