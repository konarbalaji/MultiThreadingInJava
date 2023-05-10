package com.learnjava.completableFuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @Mock
    private ProductInfoService pismock;
    @Mock
    private ReviewService rsmock;
    @Mock
    private InventoryService ismock;

    @InjectMocks
    ProductServiceUsingCompletableFuture pscfl;

    @Test
    void retrieveProductDetailsWithInventory_approach2() {

        //given
        String productId = "ABC123";
        when(pismock.retrieveProductInfo(any())).thenCallRealMethod();
        when(rsmock.retrieveReviews(any())).thenThrow(new RuntimeException());
        when(ismock.retrieveInventory(any())).thenCallRealMethod();

        //when
        Product prod = pscfl.retrieveProductDetailsWithInventory_approach2(productId);

        //then
        assertNotNull(prod);
        assertTrue(prod.getProductInfo().getProductOptions().size()>0);
        assertEquals(10, prod.getReview().getNoOfReview());

    }

    @Test
    void retrieveProductDetailsWithInventory_ProductInfoServiceError() {

        //given
        String productId = "ABC123";
        when(pismock.retrieveProductInfo(any())).thenThrow(new RuntimeException("Error here"));
        when(rsmock.retrieveReviews(any())).thenCallRealMethod();

        //then
        assertThrows(RuntimeException.class, () -> pscfl.retrieveProductDetailsWithInventory_approach2(productId));

    }

    @Test
    void retrieveProductDetailsWithInventory_InventoryFetchError() {

        //given
        String productId = "ABC123";
        when(pismock.retrieveProductInfo(any())).thenCallRealMethod();
        when(rsmock.retrieveReviews(any())).thenCallRealMethod();
        when(ismock.retrieveInventory(any())).thenThrow(new RuntimeException("Error here"));

        //then
        //assertThrows(RuntimeException.class, () -> pscfl.retrieveProductDetailsWithInventory_approach3(productId));

        Product prod = pscfl.retrieveProductDetailsWithInventory_approach3(productId);

        assertNotNull(prod);
        prod.getProductInfo().getProductOptions()
                .stream()
                .forEach(prodOpt -> {
                    assertEquals(1, prodOpt.getInventory().getCount());
                });
    }
}