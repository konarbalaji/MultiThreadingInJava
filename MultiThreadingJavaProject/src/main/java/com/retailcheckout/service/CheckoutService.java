package com.retailcheckout.service;

import com.retailcheckout.domain.checkout.Cart;
import com.retailcheckout.domain.checkout.CartItem;
import com.retailcheckout.domain.checkout.CheckoutResponse;
import com.retailcheckout.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;

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
            log("Checkout Error");
            return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
        }


        Double finalPrice = calculateFinalPrice(cart);
        log("Checkout complete & final price is : " + finalPrice);

        return new CheckoutResponse(CheckoutStatus.SUCCESS, finalPrice);
    }

    private Double calculateFinalPrice(Cart cart) {
        return cart.getCartItemList()
                    .stream()
                    .map(item -> item.getQuantity() * item.getRate())
                    .collect(Collectors.summingDouble(Double::doubleValue));

    }

    private Double calculateFinalPrice_reduce(Cart cart) {
        return cart.getCartItemList()
                .stream()
                .map(i -> i.getQuantity()*i.getRate())
                .reduce(0.0,Double::sum);

    }
}