package com.learnjava.parallelStreams;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class LinkedListSpliteratorExample {

    public List<Integer> multiplyEachValue(LinkedList<Integer> inputList, int multiplyValues, boolean isParallel){

        startTimer();
        Stream<Integer> integerStream = inputList.stream();

        if(isParallel)
            integerStream = integerStream.parallel();

        List<Integer> resultList = integerStream
                .map(integer -> integer * 2)
                .collect(Collectors.toList());

        timeTaken();

        return resultList;
    }


}
