package com.computer.catalog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductModel extends RepresentationModel<ProductModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String batch;
    @Column
    private int quantity;
    @Column
    private BigDecimal price;
    @Column
    private BigDecimal discount;
    @Column(name = "image_url")
    private String imageUrl;

}
