package com.learnjava.completableFuture;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.Review;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ProductServiceUsingCompletableFuture {

    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
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
        ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is : " + product);

    }

    public Product retrieveProductDetailsWithInventory(String productId){
        stopWatch.start();

        CompletableFuture<ProductInfo> prodFut = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> rev = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product prod = prodFut.thenCombine(rev, (productInfo, review) -> new Product(productId, productInfo, review)).join();

        stopWatch.stop();

        log("Total TimeTaken : " + stopWatch.getTime());
        return prod;
    }
}
