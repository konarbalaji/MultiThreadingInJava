package com.learnjava.forkjoin;

import com.learnjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class StringTransformExample {

    public static void main(String[] args) {
        stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        log("names : " + names);

        names.forEach(name -> {
            String newVal = addNameLengthTransform(name);
            resultList.add(newVal);
        });

        stopWatch.stop();
        log("Final result : " + resultList);
        log("Total time taken : " + stopWatch.getTime());
    }

    private static String addNameLengthTransform(String name){
        delay(500);
        return name.length() + "-" + name;
    }
}
