package com.learnjava.parallelStreams;

import com.learnjava.util.CommonUtil;
import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamsExample {

    public List<String> stringTransform(List<String> namesList){

        return namesList.stream()
                        .parallel()
                        .map(this::addNameLengthTransform)
                        .parallel()
                        .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample ex = new ParallelStreamsExample();
        CommonUtil.startTimer();
        List<String> res = ex.stringTransform(namesList);
        CommonUtil.timeTaken();
        log("Result : " + res);

    }

    private String addNameLengthTransform(String name){
        delay(500);
        return name.length() + "-" + name;
    }
}