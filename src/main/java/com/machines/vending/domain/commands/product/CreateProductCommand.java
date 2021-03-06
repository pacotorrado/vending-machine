package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.CreateProductWithGivenIdException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.models.IdInfo;
import com.machines.vending.domain.models.Product;

public interface CreateProductCommand {
    IdInfo execute(final Product product) throws NotValidProductCostException, NotValidProductNameException, CreateProductWithGivenIdException;
}
