package com.learnjava.parallelStreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.LoggerUtil.log;

public class CollectVsReduce {

    public static void main(String[] args) {

        log("Collect : " + collect());
        log("reduce : " + reduce());
    }

    public static String collect(){
        List<String> list = DataSet.namesList();

        String result = list.parallelStream()
                            .collect(Collectors.joining());

        return  result;
    }

    public static String reduce(){
        List<String> list = DataSet.namesList();

        String result = list.parallelStream()
                            .reduce("", (s1,s2)->s1+s2);

        return result;
    }
}