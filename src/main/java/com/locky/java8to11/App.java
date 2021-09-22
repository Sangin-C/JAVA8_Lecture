package com.locky.java8to11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

    public static void main(String[] args) {

        //스태틱 메소드 참조 방법
        //Greeting에 있는 hi라는 메소드를 사용하겠다.
        UnaryOperator<String> hi = Greeting::hi;
        hi.apply("locky");

        //특정 객체의 인스턴스 메소드 참조 방법
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        hello.apply("locky");

        //생성자 참조 방법 ( 입력값 X )
        Supplier<Greeting> newGreeting = Greeting::new;
        newGreeting.get();

        //생성자 참조 방법 ( 입력값 O )
        Function<String, Greeting> lockyGreeting = Greeting::new;
        lockyGreeting.apply("locky");

        //임의의 인스턴스 메소드 참조 방법
        String[] names = {"lokcy", "keesun", "toby"};
        Arrays.sort(names, String::compareToIgnoreCase);

    }
}
