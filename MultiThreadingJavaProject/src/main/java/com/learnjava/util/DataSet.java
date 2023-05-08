package com.learnjava.util;

import com.retailcheckout.domain.checkout.Cart;
import com.retailcheckout.domain.checkout.CartItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class DataSet {

    public static Cart createCart(int noOfItemsInCart){

        Cart cart = new Cart();
        List<CartItem> cartItemList = new ArrayList<>();
        IntStream.rangeClosed(1,noOfItemsInCart)
                .forEach((index) -> {
                    String cartItem = "CartItem-".concat(index + "");
                    CartItem cartItem1 = new CartItem(index, cartItem, generateRandomPrice(),index,false);
                    cartItemList.add(cartItem1);
                });

        cart.setCartItemList(cartItemList);
        return cart;

    }

    private static double generateRandomPrice() {
        int min = 50;
        int max = 100;
        return Math.random() * (max - min + 1) + min;
    }


    public static List<String> namesList(){
        return List.of("Bob","Jamie","Jill","Rick");
    }

    public static ArrayList<Integer> generateArrayList(int maxNumber) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        IntStream.rangeClosed(1, maxNumber)
                .boxed()
                .forEach(arrayList::add);

        return arrayList;


    }

    public static LinkedList<Integer> generateLinkedList(int maxNumber) {

        LinkedList<Integer> linkedList = new LinkedList<>();

        IntStream.rangeClosed(1, maxNumber)
                .boxed()
                .forEach(linkedList::add);

        return linkedList;

    }
}