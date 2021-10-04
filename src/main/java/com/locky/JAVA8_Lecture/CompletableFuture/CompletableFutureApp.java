package com.locky.JAVA8_Lecture.CompletableFuture;

import java.util.Locale;
import java.util.concurrent.*;

public class CompletableFutureApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // CompletableFuture은 자바에서 비동기 프로그래밍을 가능케하는 인터페이스이다.
        // 기존에 Future로도 가능했지만, 여러 힘든일들이 많았다.
        // Future는 외부에서 완료 시킬 수 없고, 블록킹 콜(.get())을 호출하지 않고서는 작업이 끝났을 때 콜백을 실행 할 수 없다.
        // 여러 Future들을 조합하여 사용하는것도 어렵고, 예외 처리 API도 제공해주지 않는다.
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future = executorService.submit(() -> "future");


        // .get()을 하기 전까지는 위의 결과값들을 사용할 수 없다.
        // future로 받은 결과값들은 모두 .get() 호출 이후에 작업을 해야한다.
        future.get();


        // CompletableFuture을 사용하면 Executors를 명시하지않고 그냥 바로 사용하능하다.
        // .get()메서드를 호출해야하는건 똑같다.
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("lokcy");
        completableFuture.get();


        // 리턴 값이 없을경우
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
        });
        // 위의 코드는 Future만 정의한것이기 때문에 .get()이나 .join()을 사용해야 실행된다.
        voidCompletableFuture.get();


        // 리턴 값이 있는경우
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        supplyAsync.get();


        // 콜백 사용 방법
        // 1. 값 바꾸기 ( stream의 map과 유사 )
        // 입력값도 있고 리턴값도 있는 경우
        // .thenApply()
        CompletableFuture<String> thenApply = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }).thenApply((s) -> {   //Future는 get을 호출하기전에 콜백을 정의할 수 없엇지만, CompletableFuture은 정의할 수 있다.
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase(Locale.ROOT);
        });
        thenApply.get();


        // 2. 입력값은 있지만 리턴이 없는 경우
        // .thenAccept()
        CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase(Locale.ROOT));
        });
        thenAccept.get();

        // 3. 입력값도없고 리턴도 없는 경우, 그냥 어떤 작업만 하면 되는 경우
        // .thenRuen()
        CompletableFuture<Void> thenRun = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        thenRun.get();

        // ThreadPool을 생성하지도 않고 어떻게 별도의 여러 Thread에서 동작이 가능한가???
        // ForkJoinPool때문에 가능하다.
        // JAVA7에서 도입된것인데, ForkJoinPool은 Executor를 구현한 구현체중 하나이다.
        // Executor를 사용하지 않아도 내부적으로 FormJoinPool에 있는 commonPool을 사용하게 된다.
        // 우리가 Executor를 사용해 만든 Thread Pool을 사용할 수도 있다.
        ExecutorService ThreadPool = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> forkJoinPool = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }, ThreadPool).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, ThreadPool);
        forkJoinPool.get();


        // 조합해서 사용하기
        // Future만으로는 비동기적인 작업 2개를 연결하기에 쉽지 않았다. 왜?? 콜백을 줄수 없었기 때문이다.
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });
        // 이전에는 hello.get() world.get()을 모두 호출한 뒤에 두개를 사용을 해야했다.
        // .thenCompose()를 사용하면 바로 추가적인 작업이 가능하다.

        // 두 개의 작업을 하나의 Future로 받아 올 수 있다.
        // .thenCompose()는 두 개의 작업이 의존성을 가지고 있을때 사용한다. EX) A다음에 B가 실행되어야한다.
        CompletableFuture<String> thenCompose = hello.thenCompose(s -> getWorld(s));
        thenCompose.get();

        private static CompletableFuture<String> getWorld(String message) {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("World " + Thread.currentThread().getName());
                return "World";
            });
        }

        // 두 개의 작업이 의존성을 가지고 있지 않을 경우 각각의 작업이 따로 진행되도 되는경우
        CompletableFuture<String> thenCombine = hello.thenCombine(world, (h, w) -> {
            return h + " " + w;
        });
        thenCombine.get();

        // 두 개 이상의 작업을 조합해야할때
        // .allOf() 파라미터에 모든 작업을 다 넣으면 된다.
        // 주의할점 모든 task들의 결과값의 타입이 다 같다는 보장도 없고, 어떤건 error가 날수도 있기 때문에
        // .allOf()의 리턴값은 null이 나온다.
        CompletableFuture<Void> allOf = CompletableFuture.allOf(hello, world)
                .thenAccept((result) -> {
                    System.out.println(result);
                });
        allOf.get();  // null


        // 여러 작업중 가장 빨리 끝나는 것 가져오기
        // anyOf()
        CompletableFuture<Void> anyOf = CompletableFuture.anyOf(hello, world)
                .thenAccept((result) -> System.out.println(result));
        anyOf.get();



        boolean throwsError = true;
        // 예외 처리 하기
        // .exceptionally()를 사용하면 예외를 처리할 수 있다.
        CompletableFuture<String> exceoptionally = CompletableFuture.supplyAsync(() -> {
            if(throwsError){
                throw new IllegalStateException();
            }
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error";
        });
        exceoptionally.get();


        // exceptionally()보다 더 일반적으로 쓰이는 메서드가 있는데 바로 .handle()이다.
        // handle()은 정상적으로 종료됐을때, 에러가 발생했을때 모두 사용할 수 있다.
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(() -> {
            if(throwsError){
                throw new IllegalStateException();
            }
            return "Hello";
            //첫번째는 정상적으로 종료 됐을때, 두번째는 에러가 발생했을때를 처리하기위해 BiFunction을 사용한다.
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "Error";
            }
            return result;
        });
        exceoptionally.get();


    }


}
