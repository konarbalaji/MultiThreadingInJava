package com.retailcheckout.service;

import com.retailcheckout.domain.checkout.Cart;
import com.retailcheckout.domain.checkout.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutService {

    PriceValidatorService priceValidatorService = new PriceValidatorService();

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public void checkout(Cart cart) {

        List<CartItem> priceValidationList = cart.getCartItemList()
                                                    .stream()
                                                    .map(cartItem -> {
                                                        boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                                                        cartItem.setExpired(isPriceInvalid);
                                                        return cartItem;
                                                    })
                                                    .filter(CartItem::isExpired)
                                                    .collect(Collectors.toList());

        if(priceValidationList.size()>0){

        }
    }
}