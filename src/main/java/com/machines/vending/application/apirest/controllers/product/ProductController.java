package com.machines.vending.application.apirest.controllers.product;

import com.machines.vending.application.apirest.configuration.security.AllowedRoles;
import com.machines.vending.application.apirest.controllers.BaseController;
import com.machines.vending.domain.commands.product.CreateProductCommand;
import com.machines.vending.domain.commands.product.DeleteProductCommand;
import com.machines.vending.domain.commands.product.ReadAllProductsCommand;
import com.machines.vending.domain.commands.product.ReadProductCommand;
import com.machines.vending.domain.commands.product.UpdateProductCommand;
import com.machines.vending.domain.exceptions.product.CreateProductWithGivenIdException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.exceptions.session.NoActiveSessionException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.domain.models.Role;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/products",
        consumes = "application/json",
        produces = "application/json")
public class ProductController extends BaseController {
    private final CreateProductCommand createProductCommand;
    private final DeleteProductCommand deleteProductCommand;
    private final UpdateProductCommand updateProductCommand;
    private final ReadProductCommand readProductCommand;
    private final ReadAllProductsCommand readAllProductsCommand;

    @PostMapping()
    @ResponseStatus(CREATED)
    @AllowedRoles(Role.SELLER)
    public Product createProduct(@RequestHeader(TOKEN_KEY) String token,
                                 @RequestBody Product product) throws CreateProductWithGivenIdException, NotValidProductCostException, NotValidProductNameException, NoActiveSessionException {
        final Integer userId = getUserInformationFromToken(token);
        return createProductCommand.execute(Product.builder()
                .sellerId(userId)
                .productName(product.getProductName())
                .cost(product.getCost())
                .amountAvailable(product.getAmountAvailable())
                .build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    @AllowedRoles(Role.SELLER)
    public void updateProduct(@RequestHeader(TOKEN_KEY) String token,
                              @PathVariable int id,
                              @RequestBody Product product) throws NotValidProductCostException, ProductNotFoundException, NotValidProductNameException, NoActiveSessionException {
        final Integer userId = getUserInformationFromToken(token);
        updateProductCommand.execute(Product.builder()
                .id(id)
                .sellerId(userId)
                .cost(product.getCost())
                .amountAvailable(product.getAmountAvailable())
                .build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    @AllowedRoles(Role.SELLER)
    public void deleteProduct(@RequestHeader(TOKEN_KEY) String token,
                              @PathVariable int id) throws NoActiveSessionException {
        final Integer userId = getUserInformationFromToken(token);
        deleteProductCommand.execute(Product.builder()
                .id(id)
                .sellerId(userId)
                .build());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public void getProduct(@PathVariable int id) throws ProductNotFoundException {
        readProductCommand.execute(Product.builder()
                .id(id)
                .build());
    }

    @GetMapping("")
    @ResponseStatus(OK)
    public List<Product> getAllProducts() {
        return readAllProductsCommand.execute();
    }
}
