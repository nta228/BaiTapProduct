package com.example.baitapproduct.search;


import com.example.baitapproduct.BaiTapProductApplication;
import com.example.baitapproduct.entity.Product;
import com.example.baitapproduct.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = BaiTapProductApplication.class)
@RunWith(SpringRunner.class)
class ProductSpecificationTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testProductSearch() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ProductSpecification spec1 = new ProductSpecification(new SearchCriteria("id", ":", 1));
        List<Product> productList = productRepository.findAll(spec1);
        assertTrue(productList.size() == 1);
        ProductSpecification spec2 = new ProductSpecification(new SearchCriteria("name", ":", "Alton Steuber"));
        ProductSpecification spec3 = new ProductSpecification(new SearchCriteria("gender", ":", "Female"));
        ProductSpecification spec4 = new ProductSpecification(new SearchCriteria("color", ":", "Blue"));
        ProductSpecification spec5 = new ProductSpecification(new SearchCriteria("size", ":", "L"));
        ProductSpecification spec6 = new ProductSpecification(new SearchCriteria("price", ":", 45155));
        ProductSpecification spec7 = new ProductSpecification(new SearchCriteria("createdAt", ":", LocalDateTime.parse("2022-07-10 01:10:05", formatter)));
        List<Product> productList1 = productRepository.findAll(Specification.where(spec2).and(spec2).and(spec3).and(spec3).and(spec4).and(spec5).and(spec6).and(spec7));
        assertTrue(productList1.size() == 1);
    }
}
