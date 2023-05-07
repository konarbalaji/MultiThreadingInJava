package com.learnjava.parallelStreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.xml.namespace.QName;
import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void stringTransform() {
        List<String> inputList = DataSet.namesList();

        startTimer();
        List<String> result = parallelStreamsExample.stringTransform(inputList);
        timeTaken();

        result.forEach(name -> {
            assertTrue(name.contains("-"));
        });
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void stringTransform_1(boolean isParallel) {
        List<String> inputList = DataSet.namesList();

        startTimer();
        List<String> result = parallelStreamsExample.stringTransform_1(inputList, isParallel);
        timeTaken();

        result.forEach(name -> {
            assertTrue(name.contains("-"));
        });
    }
}