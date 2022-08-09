package com.cg.controller.rest;

import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import com.cg.service.product.IProductService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

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

    @GetMapping("/account/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<UserDTO> accountDTOs = userService.findUserDTOByID(id);

        if (!accountDTOs.isPresent()) {
            return new ResponseEntity<>("Account ID :" + id + "not found" + "!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accountDTOs.get(), HttpStatus.OK);
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

    @GetMapping("/view/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> doViewUser(@PathVariable Long id) {

        try {
            Optional<UserDTO> account = userService.findUserDTOByID(id);

            if (!account.isPresent()) {
                return new ResponseEntity<>("This account is not found!", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(account.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit_user/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> doUpdateAccount(@PathVariable Long id,@Validated @RequestBody UserDTO userDTO,
                                             BindingResult bindingResult) {
        Optional<User> u = userService.findById(id);

        if (!u.isPresent()) {
            return new ResponseEntity<>("This account is not found!", HttpStatus.NOT_FOUND);
        }

//        new UserDTO().validate(userDTO,bindingResult);

        if (bindingResult.hasErrors()) {
            return AppUtils.errors(bindingResult);
        }

        try {
            User user = userDTO.toUser();

            user.setId(u.get().getId());
            user.setStatusAccount(u.get().getStatusAccount());
            user.setPassword(u.get().getPassword());

            userDTO = user.toUserDTO();

            userService.save(user);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}