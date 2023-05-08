package com.retailcheckout.service;

import com.learnjava.util.DataSet;
import com.retailcheckout.domain.checkout.Cart;
import com.retailcheckout.domain.checkout.CheckoutResponse;
import com.retailcheckout.domain.checkout.CheckoutStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void checkout_6_items() {

        //given
        Cart cart = DataSet.createCart(6);

        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        //then
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate()>0);

    }

    @Test
    void checkout_13_items() {

        //given
        Cart cart = DataSet.createCart(12);

        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        //then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());


    }
}