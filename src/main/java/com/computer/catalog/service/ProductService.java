package com.computer.catalog.service;

import com.computer.catalog.controller.ProductController;
import com.computer.catalog.dto.ProductDto;
import com.computer.catalog.model.ProductModel;
import com.computer.catalog.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductModel create(@Valid ProductDto productDto) {
        var productModel = new ProductModel();

        BeanUtils.copyProperties(productDto, productModel);

        return productRepository.save(productModel);
    }

    public ProductModel update(UUID uuid, @Valid ProductDto productDto) {
        Optional<ProductModel> product = productRepository.findById(uuid);

        if (product.isEmpty()) {
            return null;
        }

        var productModel = product.get();

        BeanUtils.copyProperties(productDto, productModel);

        return productRepository.save(productModel);
    }

    public boolean delete(UUID uuid) {
        Optional<ProductModel> product = productRepository.findById(uuid);

        if (product.isEmpty()) {
            return false;
        }

        productRepository.delete(product.get());

        return true;
    }

    public List<ProductModel> getAll() {
        List<ProductModel> products = productRepository.findAll();

        if (!products.isEmpty()) {
            for (ProductModel product : products) {
                product.add(linkTo(methodOn(ProductController.class).getById(product.getId())).withSelfRel());
            }
        }

        return products;
    }

    public ProductModel getById(UUID uuid) {
        Optional<ProductModel> product = productRepository.findById(uuid);

        if (product.isEmpty()) {
            return null;
        }

        var productModel = product.get();

        productModel.add(linkTo(methodOn(ProductController.class).getAll()).withRel("products"));

        return productModel;
    }

    public List<ProductModel> getBySearchTerm(String searchTerm) {
        List<ProductModel> products = productRepository.findBySearchTerm(searchTerm);

        if (!products.isEmpty()) {
            for (ProductModel product : products) {
                product.add(linkTo(methodOn(ProductController.class).getById(product.getId())).withSelfRel());
            }
        }

        return products;
    }

}
