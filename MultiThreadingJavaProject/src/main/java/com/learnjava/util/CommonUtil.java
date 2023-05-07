package com.learnjava.util;

import org.apache.commons.lang3.time.StopWatch;

import static java.lang.Thread.sleep;
import static com.learnjava.util.LoggerUtil.log;


public class CommonUtil {
    public static StopWatch stopWatch = new StopWatch();

    public static void delay(long ms){
        try{
            sleep(ms);
        }catch (Exception e){
            log("Exception is : " + e.getMessage());
        }
    }

    public static String transform(String s){
        CommonUtil.delay(500);
        return s.toUpperCase();
    }

    public static void startTimer(){
        stopWatch.start();
    }

    public static void timeTaken(){
        stopWatch.stop();
        log("Total time taken : " + stopWatch.getTime());
    }

    public static void stopWatchReset(){
        stopWatch.reset();
    }

    public static int noOfCores(){
        return Runtime.getRuntime().availableProcessors();
    }


}
