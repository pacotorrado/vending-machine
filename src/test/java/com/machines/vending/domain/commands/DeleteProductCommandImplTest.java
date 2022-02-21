package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.Product;
import com.machines.vending.infraestructure.persistence.entities.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteProductCommandImplTest {

    private DeleteProductCommand deleteProductCommand;

    @Mock
    private ProductRepository productRepository;

    private int id;

    @BeforeEach
    void setUp() {
        deleteProductCommand = new DeleteProductCommandImpl(productRepository);
        id = new Random().nextInt();
    }

    @Test
    void deleteProduct() {
        //given
        final Product productToDelete = Product.builder().id(id).build();

        //when
        deleteProductCommand.execute(productToDelete);

        //then
        verify(productRepository).deleteById(id);
    }
}