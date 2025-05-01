package com.example.curso.controllers;

import com.example.curso.dtos.ProductRecordDTO;
import com.example.curso.models.ProductModel;
import com.example.curso.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
        //Returns an HTTP response code 201 (Created) and the ProductModel object saved in the database
    }

    @GetMapping("/products")
    public ResponseEntity<Iterable<ProductModel>> findAll(){ //Create a method called findAll that returns an Iterable of ProductModel objects
        return ResponseEntity.ok(productRepository.findAll()); //Returns an HTTP response code 200 (OK) and all the objects saved in the database
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> FindById(@PathVariable("id") UUID id){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody @Valid ProductRecordDTO productRecordDTO){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted.");
    }
}
