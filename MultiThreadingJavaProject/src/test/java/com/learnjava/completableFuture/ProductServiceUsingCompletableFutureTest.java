package com.learnjava.completableFuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductServiceUsingCompletableFutureTest {

    private ProductInfoService productInfoService = new ProductInfoService();
    private ReviewService reviewService = new ReviewService();
    private InventoryService inventoryService = new InventoryService();

    ProductServiceUsingCompletableFuture pscf = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);

    @Test
    public void retrieveProductDetails(){

        String prodId = "ABC";
        Product prod = pscf.retrieveProductDetails(prodId);
        System.out.println("prod : " + prod);

        assertNotNull(prod);
        assertTrue(prod.getProductInfo().getProductOptions().size()>0);
        assertNotNull(prod.getReview());

    }

    @Test
    public void retrieveProductDetails_approach2(){

        String prodId = "ABC";
        CompletableFuture<Product> cfprod = pscf.retrieveProductDetails_approach2(prodId);
        Product prod = cfprod.join();

        assertNotNull(prod);
        assertTrue(prod.getProductInfo().getProductOptions().size()>0);
        assertNotNull(prod.getReview());

    }

    @Test
    public void retrieveProductDetailsWithInventory(){

        String prodId = "ABC";
        Product prod = pscf.retrieveProductDetailsWithInventory(prodId);
        System.out.println("prod : " + prod);

        assertNotNull(prod);
        assertTrue(prod.getProductInfo().getProductOptions().size()>0);
        assertNotNull(prod.getReview());

    }

    @Test
    public void retrieveProductDetailsWithInventory_approach2(){

        String prodId = "ABC";
        Product prod = pscf.retrieveProductDetailsWithInventory_approach2(prodId);
        System.out.println("prod : " + prod);

        assertNotNull(prod);
        assertTrue(prod.getProductInfo().getProductOptions().size()>0);
        assertNotNull(prod.getReview());

    }

}
