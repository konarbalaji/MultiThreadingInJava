package com.learnjava.completableFuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    private HelloWorldService hws;

    public String helloWorld_approach1(){
        String hello = hws.hello();
        String world = hws.hello();
        return hello+world;
    }

    public String helloWorld_multiple_async_calls(){

        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());

        String hw = hello
                        .thenCombine(world, (h, w) -> h+w)
                        .thenApply(String::toUpperCase)
                        .join();

        timeTaken();

        return hw;

    }

    public String helloWorld_3_async_calls(){

        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            log("Inside hiCompletableFuture");
            return "Hi CompletableFuture!";
        });

        String hw = hello
                .thenCombine(world, (h, w) -> h+w)
                .thenCombine(hiCompletableFuture, (prev, curr) -> prev+curr)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return hw;

    }

    public String helloWorld_4_async_calls(){

        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            log("Inside hiCompletableFuture");
            return "Hi CompletableFuture!";
        });
        CompletableFuture<String> testFut = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            log("Inside testFut");
            return " testFut Here ";
        });

        String hw = hello
                .thenCombine(world, (h, w) -> h+w)
                .thenCombine(hiCompletableFuture, (prev, curr) -> prev+curr)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return hw;

    }



    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.hws = helloWorldService;
    }

    public CompletableFuture<String> helloWorld(){
        return CompletableFuture.supplyAsync(hws::HelloWorld) // runs this in a common fork-join pool
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloWorld_thenCompose (){

        return CompletableFuture.supplyAsync(hws::HelloWorld) // runs this in a common fork-join pool
                .thenCompose((prev) -> hws.worldFuture(prev))
                .thenApply(String::toUpperCase);

    }

    public static void main(String[] args) {

        HelloWorldService helloWorldService = new HelloWorldService();

        CompletableFuture.supplyAsync(helloWorldService::HelloWorld) // runs this in a common fork-join pool
                .thenApply(String::toUpperCase)
                .thenAccept((result) -> {
                    log("Result is " + result);
                }).join();
        log("Done!");

    }
}