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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;



    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> showListProduct() {
        List<ProductDTO> productDTOs = productService.findAllProductDTO();

        if(productDTOs.isEmpty()) {
            return new ResponseEntity<>("List is empty!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @GetMapping("/list_user")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> showListUser() {
        List<UserDTO> userDTOs = userService.findAllUserDTO();

        if(userDTOs.isEmpty()) {
            return new ResponseEntity<>("List is empty!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<ProductDTO> productDTOs = productService.findProductDTOByID(id);

        if (!productDTOs.isPresent()) {
            return new ResponseEntity<>("Wine ID :" + id + "not found" + "!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDTOs.get(), HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<UserDTO> accountDTOs = userService.findUserDTOByID(id);

        if (!accountDTOs.isPresent()) {
            return new ResponseEntity<>("Account ID :" + id + "not found" + "!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accountDTOs.get(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doAdd(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        new ProductDTO().validate(productDTO, bindingResult);


        if (bindingResult.hasErrors()) {
            return AppUtils.errors(bindingResult);
        }

        Product product = productDTO.toProduct();
        product.setId(0L);

        try {
            product = productService.save(product);

            return new ResponseEntity<>(product.toProductDTO(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create_account")
    public ResponseEntity<?> doCreateAccount(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {



        if (bindingResult.hasErrors()) {
            return AppUtils.errors(bindingResult);
        }

        User user = userDTO.toUser();
        user.setId(0L);

        try {
            user = userService.save(user);

            return new ResponseEntity<>(user.toUserDTO(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doUpdate(@PathVariable Long id,@Validated @RequestBody ProductDTO productDTO,
                                    BindingResult bindingResult) {
        Optional<Product> p = productService.findById(id);

        if (!p.isPresent()) {
            return new ResponseEntity<>("This wine is not found!", HttpStatus.NOT_FOUND);
        }

        new ProductDTO().validate(productDTO,bindingResult);

        if (bindingResult.hasErrors()) {
            return AppUtils.errors(bindingResult);
        }

        try {
            Product product = productDTO.toProduct();

            product.setId(p.get().getId());
            product.setDeleted(p.get().isDeleted());

            productDTO = product.toProductDTO();

            productService.save(product);

            return new ResponseEntity<>(productDTO, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update_account/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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

    @DeleteMapping("/block/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doBlock(@PathVariable Long id, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return AppUtils.errors(bindingResult);
        }

        try{
            productService.remove(id);

            return new ResponseEntity<>(id,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/block_account/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doBlockAccount(@PathVariable Long id){

        Optional<UserDTO> userDTO = userService.findUserDTOByID(id);



        if (!userDTO.isPresent()) {
            return new ResponseEntity<>("The account ID:" + id + " is not found!", HttpStatus.NO_CONTENT);
        }

        User user = userDTO.get().toUser();


        try{
            if(user.getStatusAccount().getId() == 1) {
                userService.blockAccount(id);
            }else {
                userService.unblockAccount(id);
            }

            user = userService.findById(id).get();


            return new ResponseEntity<>(user.toUserDTO(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Server error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody String searchInput) {
        searchInput = searchInput.replace('"',' ').trim().toLowerCase();
        List<ProductDTO> listSearch = productService.search(searchInput);
        if(listSearch.size() == 0) {
            return new ResponseEntity<>("Danh sach trong", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listSearch,HttpStatus.OK);
    }
}
