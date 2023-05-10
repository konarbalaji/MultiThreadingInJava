package com.learnjava.completableFuture;

import com.learnjava.domain.*;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ProductServiceUsingCompletableFuture {

    private ProductInfoService productInfoService;
    private ReviewService reviewService;
    private InventoryService inventoryService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService, InventoryService inventoryService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
    }

    public Product retrieveProductDetails(String productId){
        stopWatch.start();

        CompletableFuture<ProductInfo> prodFut = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> rev = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product prod = prodFut.thenCombine(rev, (productInfo, review) -> new Product(productId, productInfo, review)).join();

        stopWatch.stop();

        log("Total TimeTaken : " + stopWatch.getTime());
        return prod;
    }

    public CompletableFuture<Product> retrieveProductDetails_approach2(String productId){
        stopWatch.start();

        CompletableFuture<ProductInfo> prodFut = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> rev = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        CompletableFuture<Product> prod = prodFut.thenCombine(rev, (productInfo, review) -> new Product(productId, productInfo, review));

        stopWatch.stop();

        log("Total TimeTaken : " + stopWatch.getTime());
        return prod;
    }

    public static void main(String[] args) {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        InventoryService inventoryService = new InventoryService();
        ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is : " + product);

    }

    public Product retrieveProductDetailsWithInventory(String productId){
        stopWatch.start();

        CompletableFuture<ProductInfo> prodFut = CompletableFuture
                .supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                .thenApply(prodInfo -> {
                    prodInfo.setProductOptions(updateInventory(prodInfo));
                    return prodInfo;
                });


        CompletableFuture<Review> rev = CompletableFuture
                .supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product prod = prodFut.thenCombine(rev, (productInfo, review) -> new Product(productId, productInfo, review)).join();

        stopWatch.stop();

        log("Total TimeTaken : " + stopWatch.getTime());
        return prod;
    }

    private List<ProductOption> updateInventory(ProductInfo prodInfo) {

        List<ProductOption> prodInfolist = prodInfo.getProductOptions()
                                                .stream()
                                                .map(productOption -> {
                                                    Inventory inventory = inventoryService.retrieveInventory(productOption);
                                                    productOption.setInventory(inventory);
                                                    return productOption;
                                                }).collect(Collectors.toList());

        return prodInfolist;

        /*prodInfo.getProductOptions()
                    .forEach(prodOpt -> {
                        prodOpt.setInventory(inventoryService.retrieveInventory(prodOpt));
                    });

        return prodInfo;*/

    }
}
