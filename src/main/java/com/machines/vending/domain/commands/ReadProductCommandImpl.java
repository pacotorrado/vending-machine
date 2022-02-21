package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.ProductNotFoundException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infraestructure.persistence.entities.ProductMapper;
import com.machines.vending.infraestructure.persistence.entities.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadProductCommandImpl implements ReadProductCommand {
    private final ProductRepository productRepository;

    @Override
    public Product execute(final Product product) throws ProductNotFoundException {
        return ProductMapper
                .fromEntity(productRepository.findById(product.getId()).orElseThrow(ProductNotFoundException::new))
                .toModel();
    }
}