package com.learnjava.parallelStreams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReduceExampleTest {

    ReduceExample reduceExample = new ReduceExample();

    @Test
    void reduce_sum_parallelStream() {

        //given
        List<Integer> inputList = List.of(1,2,3,4,5,6,7,8);

        //when
        int sum = reduceExample.reduce_sum_parallelStream(inputList);

        //test
        assertEquals(36, sum);
    }

    @Test
    void reduce_multiply_parallelStream() {

        //given
        List<Integer> inputList = List.of(1,2,3,4,5,6,7,8);

        //when
        int product = reduceExample.reduce_multiply_parallelStream(inputList);

        //then
        assertEquals(40320,product);
    }
}