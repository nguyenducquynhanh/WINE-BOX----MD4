package com.cg.controller.rest;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private IProductService productService;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> showListProduct() {
        List<ProductDTO> productDTOs = productService.findAllProductDTO();

        if(productDTOs.isEmpty()) {
            return new ResponseEntity<>("List is empty!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")

    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<ProductDTO> productDTOs = productService.findProductDTOByID(id);

        if (!productDTOs.isPresent()) {
            return new ResponseEntity<>("Wine ID :" + id + "not found" + "!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDTOs.get(), HttpStatus.OK);
    }


    @GetMapping("/read/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> doRead(@PathVariable Long id) {

        try {
            Optional<Product> product = productService.findById(id);

            if (!product.isPresent()) {
                return new ResponseEntity<>("This wine is not found!", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}