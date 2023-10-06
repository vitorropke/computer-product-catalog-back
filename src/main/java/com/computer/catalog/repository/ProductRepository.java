package com.computer.catalog.repository;

import com.computer.catalog.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    @Query(value =
            "SELECT * FROM products WHERE " +
                    "brand ILIKE CONCAT('%', :searchTerm, '%') OR " +
                    "model ILIKE CONCAT('%', :searchTerm, '%') OR " +
                    "batch ILIKE CONCAT('%', :searchTerm, '%');",
            nativeQuery = true)
    List<ProductModel> findBySearchTerm(String searchTerm);

}
