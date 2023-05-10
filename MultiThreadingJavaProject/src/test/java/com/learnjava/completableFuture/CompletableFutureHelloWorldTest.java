package com.learnjava.completableFuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(hws);

    @Test
    public void helloWorld(){
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld();

        completableFuture.
                thenAccept(s -> assertEquals("HELLO WORLD",s))
                .join();
    }

    @Test
    public void helloWorld_multiple_async_calls(){
        String res = completableFutureHelloWorld.helloWorld_multiple_async_calls();
        assertEquals("HELLOWORLD",res);
    }

    @Test
    public void helloWorld_3_async_calls(){
        String res = completableFutureHelloWorld.helloWorld_3_async_calls();
        assertEquals("HELLOWORLDHI COMPLETABLEFUTURE!",res);
    }

    @Test
    public void helloWorld_4_async_calls(){
        String res = completableFutureHelloWorld.helloWorld_4_async_calls();
        assertEquals("HELLOWORLDHI COMPLETABLEFUTURE!",res);
    }

    @Test
    public void helloWorld_thenCompose(){

        startTimer();
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld_thenCompose();

        completableFuture.
                thenAccept(s -> assertEquals("HELLO WORLD WORLD!",s))
                .join();

        timeTaken();
    }

}