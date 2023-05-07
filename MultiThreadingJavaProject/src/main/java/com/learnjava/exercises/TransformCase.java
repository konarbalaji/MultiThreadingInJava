package com.learnjava.exercises;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

public class TransformCase {

    public static void main(String[] args) {

        List<String> nameList = DataSet.namesList();

        List<String> res = nameList.parallelStream()
                                    .map(TransformCase::string_toLowerCase)
                                    .collect(Collectors.toList());

        System.out.println(res);
    }

    public static String string_toLowerCase(String s){
        return  s.toLowerCase();
    }
}
