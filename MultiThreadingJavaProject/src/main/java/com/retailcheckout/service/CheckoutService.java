package com.retailcheckout.service;

import com.retailcheckout.domain.checkout.Cart;
import com.retailcheckout.domain.checkout.CartItem;
import com.retailcheckout.domain.checkout.CheckoutResponse;
import com.retailcheckout.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class CheckoutService {

    PriceValidatorService priceValidatorService = new PriceValidatorService();

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart) {

        startTimer();

        List<CartItem> priceValidationList = cart.getCartItemList()
                                                    .parallelStream()
                                                    .map(cartItem -> {
                                                        boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                                                        cartItem.setExpired(isPriceInvalid);
                                                        return cartItem;
                                                    })
                                                    .filter(CartItem::isExpired)
                                                    .collect(Collectors.toList());

        timeTaken();

        if(priceValidationList.size()>0){
            return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
        }



        return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }
}