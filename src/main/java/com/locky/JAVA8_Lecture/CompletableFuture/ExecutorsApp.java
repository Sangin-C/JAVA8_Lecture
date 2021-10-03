package com.locky.JAVA8_Lecture.CompletableFuture;

import java.util.concurrent.*;

public class ExecutorsApp {

    public static void main(String[] args) {

        //Executors를 사용해 Thread를 생성 방법
        ExecutorService executorSingleService = Executors.newSingleThreadExecutor();
        executorSingleService.execute(() -> System.out.println("Thread : " + Thread.currentThread().getName()));

        //ExecutorService는 Thread와 달리 어떤 작업을 시작하고 다음 작업이 들어올때까지 계속 대기를한다
        //.shutdown() 메서드를 통해 종료시켜줘야한다.
        executorSingleService.shutdown();


        //Thread Pool안에 2개의 Thread를 만들어서 task들을 처리한다.
        //대기중인 task들은 BlockingQueue에 쌓여서 순서를 기다린다.
        ExecutorService executorThreadPoolService = Executors.newFixedThreadPool(2);
        executorThreadPoolService.execute(() -> System.out.println("Thread1 : " + Thread.currentThread().getName()));
        executorThreadPoolService.execute(() -> System.out.println("Thread2 : " + Thread.currentThread().getName()));
        executorThreadPoolService.execute(() -> System.out.println("Thread3 : " + Thread.currentThread().getName()));
        executorThreadPoolService.execute(() -> System.out.println("Thread4 : " + Thread.currentThread().getName()));
        executorThreadPoolService.execute(() -> System.out.println("Thread5 : " + Thread.currentThread().getName()));

        executorThreadPoolService.shutdown();


        //스케쥴을 이용해 반복적으로 실행 시킬 수 있다.
        ScheduledExecutorService executorScheduledService = Executors.newSingleThreadScheduledExecutor();
        executorScheduledService.scheduleAtFixedRate(() -> System.out.println("Thread : " + Thread.currentThread().getName()), 1, 2, TimeUnit.SECONDS);
    }
}
