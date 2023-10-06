package com.computer.catalog;

import com.computer.catalog.controller.ProductController;
import com.computer.catalog.dto.ProductDto;
import com.computer.catalog.model.ProductModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@SpringBootTest
class CatalogApplicationTests {

    @Test
    void contextLoads() {
        String brand = "Intel";
        String model = "i3-7100";
        String batch = "abc123";
        int quantity = 11;
        BigDecimal price = BigDecimal.valueOf(210.24);
        BigDecimal discount = BigDecimal.valueOf(0.0);
        String imageUrl = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSO1msyr8xj5xzNLNTZUZB84xxJxSu3_wmnYEo2hf4YivJSH5CUFTnTe_s5JSIUFxvnHAZwbBbn4jM113--DCCsB3V5YmWt1Q";

        ProductDto productDto = new ProductDto(brand, model, batch, quantity, price, discount, imageUrl);

        ProductController productController = new ProductController(null);

        ResponseEntity<ProductModel> createdProduct = productController.create(productDto);
        ResponseEntity<Object> updatedProduct = productController.update(UUID.fromString("abc abc"), productDto);
        ResponseEntity<Object> deletedProductStatus = productController.delete(UUID.fromString("abc abc"));
        ResponseEntity<List<ProductModel>> products = productController.getAll();
        ResponseEntity<Object> foundProduct = productController.getById(UUID.fromString("abc abc"));
        ResponseEntity<List<ProductModel>> productsFromSearchTerm = productController.getBySearchTerm("in");

        Assertions.assertEquals(11, Objects.requireNonNull(createdProduct.getBody()).getQuantity());
        Assertions.assertEquals(brand, updatedProduct.getBody());
        Assertions.assertEquals(true, deletedProductStatus.getBody());
        Assertions.assertEquals(brand, Objects.requireNonNull(products.getBody()).get(0).getBrand());
        Assertions.assertEquals(brand, foundProduct.getBody());
        Assertions.assertEquals(brand, Objects.requireNonNull(productsFromSearchTerm.getBody()).get(0).getBrand());
    }

}
