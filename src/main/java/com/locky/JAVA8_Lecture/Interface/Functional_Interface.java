package com.locky.JAVA8_Lecture.Interface;

import java.util.function.*;

public class Functional_Interface {

    public static void main(String[] args) {

        //Function<T, R> - R apply(T var1);
        //Function 인터페이스 -> 파라미터를 1개 받고, 1개의 값을 리턴한다.
        //Fucntion 인터페이스의 활용 메서드로 andThen(), compose() 등이 있다.
        //andThen() -> a.andThen(b) a 함수를 먼저 실행하고 이어서 b 함수를 실행해 하나의 리턴 값을 만든다.
        //compose() -> a.compose(b) 파라미터로 넘긴 b를 먼저 실행하고 이어어 a 함수를 실행해 하나의 리턴 값을 만든다.
        Function<Integer, Integer> plus10 = (i) -> i+10;
        plus10.apply(10);
        Function<Integer, Integer> multiply2 = (i) -> i*2;
        multiply2.apply(10);
        multiply2.andThen(plus10).apply(10);
        plus10.compose(multiply2).apply(10);


        //BiFunction<T, U, R> - R apply(T var1, U var2);
        //BiFunction 인터페이스 -> 파라미터를 2개 받고, 1개의 값을 리턴한다.
        BiFunction<Integer, Integer, Integer> plusAB = (a, b) -> a + b;
        plusAB.apply(10, 10);


        //Consumer<T> - void accept(T var1);
        //Consumer 인터페이스 -> 파라미터를 1개 받고, 아무것도 리턴하지 않는다.
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);


        //Supplier<T> - T get();
        //Supplier 인터페이스 -> 파라미터 없이 어떤 값만 리턴 한다.
        Supplier<Integer> get10 = () -> 10;
        get10.get();


        //Predicate<T> - boolean test(T var1);
        //Supplier 인터페이스 -> 파라미터 1개를 받고, true, false를 리턴해준다.
        Predicate<String> startsWithLokcy = (s) -> s.startsWith("locky");
        startsWithLokcy.test("locky");
        Predicate<Integer> isEven = (i) -> i%2 == 0;
        isEven.test(2);
    }
}
