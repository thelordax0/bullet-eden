package org.thelordax0.bulleteden;

public class Game implements Runnable{
    public static int width=300;
    public static  int height=width/16*9;
    public static int scale=3;

    private Thread thread;

    public synchronized void start(){
        thread=new Thread(this,"Display");
        thread.start();
    }
    public synchronized void stop(){
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void run() {

    }
}
