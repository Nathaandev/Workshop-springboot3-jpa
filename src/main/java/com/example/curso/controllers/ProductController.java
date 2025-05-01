package com.example.curso.controllers;

import com.example.curso.dtos.ProductRecordDTO;
import com.example.curso.models.ProductModel;
import com.example.curso.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    //Indicates to SpringBoot that this method will receive a POST request
    @PostMapping("/products")
    //Indicates that this method will be executed when the endpoint /products is called with the method HTTP Post

    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO){
        //The ResponseEntity Contains the HTTP response code and the body of the response
        //The RequestBody annotation will take the JSON and transform it into a ProductRecordDTO object (Name and Price)
        //The Valid annotation activates the @NotNull and @NotBlank annotations on the ProductRecordDTO class
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productModel); //Will copy the values from the ProductRecordDTO to the ProductModel (Name and Price)

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
        //Returns a HTTP response code 201 (Created) and the ProductModel object saved in the database
    }

}
