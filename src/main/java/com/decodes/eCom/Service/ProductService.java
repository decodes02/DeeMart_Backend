package com.decodes.eCom.Service;

import com.decodes.eCom.Model.Product;
import com.decodes.eCom.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        if (!product.isProductAvailable()) {
            product.setProductAvailable(false); // Default to false if not provided
        }
        return repo.save(product);
    }


    public void deleteProduct(int id) {
        repo.deleteById(id);
    }


    public Product updateProduct(int id, Product product, MultipartFile imageFile) {
        return repo.findById(id).map(product1 -> {
            product1.setName(product.getName());
            product1.setBrand(product.getBrand());
            product1.setCategory(product.getCategory());
            product1.setDescription(product.getDescription());
            product1.setPrice(product.getPrice());
            product1.setStockQuantity(product.getStockQuantity());
            product1.setProductAvailable(product.isProductAvailable());
            product1.setReleaseDate(product.getReleaseDate());
            product1.setImageName(imageFile.getOriginalFilename());
            product1.setImageType(imageFile.getContentType());
            try {
                product1.setImageData(imageFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return repo.save(product1);
        }).orElse(null);
    }

    public List<Product> searchProduct(String keyword) {
        return repo.searchProduct(keyword);
    }
}
