package misc;

import lombok.NoArgsConstructor;

public class ThreadExample {

    private static String result = "";

    public static void main(String[] args)  {

        try{

            Thread helloThread = new Thread(ThreadExample::hello);
            Thread worldThread = new Thread(ThreadExample::world);

            //Starting the thread
            helloThread.start();
            worldThread.start();

            //Joining the thread(waiting for the threads to finish)
            helloThread.join();
            worldThread.join();

            System.out.println("Result is : " + result);

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void hello() {
        delay(500);
        result=result.concat("Hello");
    }

    public static void world() {
        delay(510);
        result=result.concat(" World");
    }

    private static void delay(int i)  {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}