package com.learnjava.service;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.LoggerUtil.log;

public class HelloWorldService {

    public String HelloWorld(){
        delay(1000);
        log("inside helloworld");
        return "hello world";
    }

    public String hello(){
        delay(2000);
        log("inside hello");
        return "hello";
    }

    public String world(){
        delay(2000);
        log("inside world");
        return "world";
    }
}
