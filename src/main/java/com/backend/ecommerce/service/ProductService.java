package com.backend.ecommerce.service;


import com.backend.ecommerce.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    ProductInfo findOne(int productId);

    // All selling products
    Page<ProductInfo> findUpAll(Pageable pageable);
    // All products
    Page<ProductInfo> findAll(Pageable pageable);
    // All products in a category
    Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable);

    // increase stock
    void increaseStock(int productId, int amount);

    //decrease stock
    void decreaseStock(int productId, int amount);

    ProductInfo offSale(int productId);

    ProductInfo onSale(int productId);

    ProductInfo update(ProductInfo productInfo);
    ProductInfo save(ProductInfo productInfo);

    void delete(int productId);


}
