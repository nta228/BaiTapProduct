package com.example.baitapproduct.restApi;

import com.example.baitapproduct.entity.Product;
import com.example.baitapproduct.repository.ProductRepository;
import com.example.baitapproduct.search.ProductSpecification;
import com.example.baitapproduct.search.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductRestApi {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> getList(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String color,
        @RequestParam(required = false) String size,
        @RequestParam(required = false) String gender,
        @RequestParam(required = false, defaultValue = "0") Integer status,
        @RequestParam(required = false, defaultValue = "0") Double price
    )
    {
        Specification<Product> productSpecification = Specification.where(null);
        if( name != null && name.length() > 0) {
            productSpecification.and(new ProductSpecification(new SearchCriteria("name", ":", name)));
        }
        if(color != null && color.length() > 0) {
            productSpecification.and(new ProductSpecification(new SearchCriteria("color", ":", color)));
        }
        if(size != null && size.length() > 0) {
            productSpecification.and(new ProductSpecification(new SearchCriteria("size", ":", size)));
        }
        if(gender != null && gender.length() > 0) {
            productSpecification.and(new ProductSpecification(new SearchCriteria("gender", ":", gender)));
        }
        if(status != 0) {
            productSpecification.and(new ProductSpecification(new SearchCriteria("status", ":", gender)));
        }
        return ResponseEntity.ok(productRepository.findAll(productSpecification));
    }
}
