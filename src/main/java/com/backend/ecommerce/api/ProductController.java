package com.backend.ecommerce.api;


import com.backend.ecommerce.config.FileUploadUtil;
import com.backend.ecommerce.entity.ProductInfo;
import com.backend.ecommerce.service.CategoryService;
import com.backend.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    /**
     * Show All Categories
     */

    @GetMapping("/product")
    public Page<ProductInfo> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findAll(request);
    }

    @GetMapping("/product/{productId}")
    public ProductInfo showOne(@PathVariable("productId") int productId) {

        ProductInfo productInfo = productService.findOne(productId);

//        // Product is not available
//        if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
//            productInfo = null;
//        }

        return productInfo;
    }

    @PostMapping(value = "/product" )
    public ResponseEntity create(@Valid @RequestBody ProductInfo product,
                                 BindingResult bindingResult) {
        ProductInfo productIdExists = productService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }

        return ResponseEntity.ok(productService.save(product).getProductId());
    }
    @PostMapping(value = "/product/image/{id}" )
    public ResponseEntity create( @PathVariable int id,
                                 @RequestParam("file") MultipartFile multipartFile) {
        ProductInfo p = productService.findOne(id);

        System.err.println("error "+multipartFile.getSize());
        if(!multipartFile.isEmpty()){
            String orgFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
            String uploadDir = "/Applications/XAMPP/xamppfiles/htdocs/productsImgs/";
            String fileName = "product-"+p.getProductId()+ext;
            FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
            p.setProductIcon("http://127.0.0.1/productsImgs/"+fileName);

        }
        return ResponseEntity.ok(productService.save(p).getProductId());
    }
    @PutMapping("/product/edit/{id}")
    public ResponseEntity edit(@PathVariable("id") int productId,
                               @Valid @RequestBody ProductInfo product,
                               BindingResult bindingResult) {


        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

}
