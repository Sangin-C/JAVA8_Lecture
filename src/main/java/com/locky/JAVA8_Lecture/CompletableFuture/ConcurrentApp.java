package com.locky.JAVA8_Lecture.CompletableFuture;

public class ConcurrentApp {

    //Concurrent 소프트웨어?? 동시에 여러 작업을 할 수 있는 소프트웨어
    //자바에서는 멀티프로세싱(ProcessBuilder), 멀티쓰레드를 지원한다.
    public static void main(String[] args){
        Thread thread = new Thread(() -> {
            while(true) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    //sleep -> 쓰레드를 대기시킨다.
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        });

        //.start() 스레드를 실행시킨다.
        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());

        try {
            //.join()은 위의 thread가 끝날때까지 기다린다.
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread + "is finished");

        //.interrupt()는 종료시키는 메서드가 따로 있지는 않지만 interrupt()를 사용해서 InterrutedException을 터트려 종료시킬수 있다.
        thread.interrupt();

    }

    //1. 쓰레드를 extends 받는 방법
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread : " + Thread.currentThread().getName());
        }
    }

}
