package com.computer.catalog.controller;

import com.computer.catalog.dto.ProductDto;
import com.computer.catalog.model.ProductModel;
import com.computer.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductModel> create(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID uuid, @RequestBody ProductDto productDto) {
        ProductModel productModel = productService.update(uuid, productDto);

        if (productModel != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productModel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID uuid) {
        if (productService.delete(uuid)) {
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado.");
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID uuid) {
        ProductModel productModel = productService.getById(uuid);

        if (productModel != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productModel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado.");
        }
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<List<ProductModel>> getBySearchTerm(@PathVariable(value = "searchTerm") String searchTerm) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getBySearchTerm(searchTerm));
    }

}
