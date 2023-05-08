package com.learnjava.parallelStreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamPerformanceTest {

    ParallelStreamPerformance intStreamExample = new ParallelStreamPerformance();

    @Test
    void sum_using_intstream() {

        int sum = intStreamExample.sum_using_intstream(1000000, false);
        System.out.println("sum : " + sum);

        assertEquals(1784293664, sum);

    }

    @Test
    void sum_using_intstream_parallel() {

        int sum = intStreamExample.sum_using_intstream(1000000, true);
        System.out.println("sum : " + sum);

        assertEquals(1784293664, sum);

    }

    @Test
    void sum_using_list() {

        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        int sum = intStreamExample.sum_using_list(inputList,false);
        System.out.println("sum using list : " + sum);

        assertEquals(1784293664, sum);
    }

    @Test
    void sum_using_list_parallel() {

        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        int sum = intStreamExample.sum_using_list(inputList,true);
        System.out.println("sum using list : " + sum);

        assertEquals(1784293664, sum);
    }

    @Test
    void sum_using_iterate() {

        int size = 1000000;
        int sum = intStreamExample.sum_using_iterate(size, false);
        System.out.println("sum using iterate : " + sum);
    }

    @Test
    void sum_using_iterate_parallel() {

        int size = 1000000;
        int sum = intStreamExample.sum_using_iterate(size, true);
        System.out.println("sum using iterate : " + sum);
    }
}