package com.cg.controller.rest;

import com.cg.model.Category;
import com.cg.model.Gender;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import com.cg.service.category.ICategoryService;
import com.cg.service.gender.IGenderService;
import com.cg.service.product.IProductService;
import com.cg.service.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/gender")
public class GenderRestController {

    @Autowired
    private IGenderService genderService;

    @GetMapping
    public ResponseEntity<?> showListGender() {
        Iterable<Gender> gender = genderService.findAll();

        if(gender == null) {
            return new ResponseEntity<>("List is empty!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gender, HttpStatus.OK);
    }
}
