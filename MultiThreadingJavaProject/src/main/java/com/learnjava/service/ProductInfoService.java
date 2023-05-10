package com.learnjava.service;

import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.ProductOption;

import java.util.List;

import static com.learnjava.util.CommonUtil.delay;

public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId){
        delay(1000);
        List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699),
                                                    new ProductOption(2, "128GB", "Black", 749),
                                                    new ProductOption(3, "128GB", "Black", 749),
                                                    new ProductOption(4, "128GB", "Black", 749),
                                                    new ProductOption(5, "128GB", "Black", 749),
                                                    new ProductOption(6, "128GB", "Black", 749));

        return ProductInfo.builder().productId(productId)
                .productOptions(productOptions)
                .build();

    }
}
