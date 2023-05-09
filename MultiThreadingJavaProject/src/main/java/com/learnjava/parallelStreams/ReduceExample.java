package com.learnjava.parallelStreams;

import java.util.List;

public class ReduceExample {

    public int reduce_sum_parallelStream(List<Integer> inputList){

        int sum = inputList
                .stream()
                .reduce(0, (a,b) -> a+b);

        return sum;
    }

    public int reduce_multiply_parallelStream(List<Integer> inputList){

        int product = inputList
                .parallelStream()
                .reduce(1, (a,b) -> a*b);

        return product;
    }
}
