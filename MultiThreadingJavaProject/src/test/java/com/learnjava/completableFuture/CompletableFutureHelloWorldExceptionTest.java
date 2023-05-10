package com.learnjava.completableFuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;

    @Test
    void helloWorld_3_async_calls_handle() {

        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String res = hwcfe.helloWorld_3_async_calls_handle();

        assertEquals("WORLDHI COMPLETABLEFUTURE!", res);
        assertNotNull(res);

        //then

    }

    @Test
    void helloWorld_3_async_calls_handle_2() {

        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        //when
        String res = hwcfe.helloWorld_3_async_calls_handle();

        assertEquals("HI COMPLETABLEFUTURE!", res);
        assertNotNull(res);

        //then
    }

    @Test
    void helloWorld_3_async_calls_handle_3() {

        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String res = hwcfe.helloWorld_3_async_calls_handle();

        assertEquals("HELLOWORLDHI COMPLETABLEFUTURE!", res);
        assertNotNull(res);

        //then
    }

    @Test
    void helloWorld_3_async_calls_exceptionally() {

        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String res = hwcfe.helloWorld_3_async_calls_exceptionally();

        assertEquals("HELLOWORLDHI COMPLETABLEFUTURE!", res);
        assertNotNull(res);

        //then
    }

    @Test
    void helloWorld_3_async_calls_exceptionally_2() {

        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        //when
        String res = hwcfe.helloWorld_3_async_calls_exceptionally();

        assertEquals("HI COMPLETABLEFUTURE!", res);
        assertNotNull(res);

        //then
    }

    @Test
    void helloWorld_3_async_calls_whenhandle() {

        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String res = hwcfe.helloWorld_3_async_calls_whenhandle();

        assertEquals("HELLOWORLDHI COMPLETABLEFUTURE!", res);
        assertNotNull(res);

        //then
    }

    @Test
    void helloWorld_3_async_calls_whenhandle_2() {

        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        //when
        String res = hwcfe.helloWorld_3_async_calls_whenhandle();

        assertEquals("HI COMPLETABLEFUTURE!", res);
        assertNotNull(res);

        //then
    }


}