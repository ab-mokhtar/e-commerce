package com.backend.ecommerce.service.impl;



import com.backend.ecommerce.entity.ProductInfo;
import com.backend.ecommerce.enums.ProductStatusEnum;
import com.backend.ecommerce.repository.ProductInfoRepository;
import com.backend.ecommerce.service.CategoryService;
import com.backend.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public ProductInfo findOne(int productId) {

        ProductInfo productInfo = productInfoRepository.findByProductId(productId);
        return productInfo;
    }

    @Override
    public Page<ProductInfo> findUpAll(Pageable pageable) {
        return productInfoRepository.findAllByProductStatusOrderByProductIdAsc(ProductStatusEnum.UP.getCode(),pageable);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAllByOrderByProductId(pageable);
    }

    @Override
    public Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable) {
        return productInfoRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
    }

    @Override
    @Transactional
    public void increaseStock(int productId, int amount) {
        ProductInfo productInfo = findOne(productId);


        int update = productInfo.getProductStock() + amount;
        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(int productId, int amount) {
        ProductInfo productInfo = findOne(productId);


        int update = productInfo.getProductStock() - amount;

        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo offSale(int productId) {
        ProductInfo productInfo = findOne(productId);

        if (productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
        }

        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo onSale(int productId) {
        ProductInfo productInfo = findOne(productId);

        if (productInfo.getProductStatus() == ProductStatusEnum.UP.getCode()) {
        }

        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo update(ProductInfo productInfo) {

        // if null throw exception
        categoryService.findByCategoryType(productInfo.getCategoryType());
        if(productInfo.getProductStatus() > 1) {
        }


        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return update(productInfo);
    }

    @Override
    public void delete(int productId) {
        ProductInfo productInfo = findOne(productId);
        productInfoRepository.delete(productInfo);

    }


}
