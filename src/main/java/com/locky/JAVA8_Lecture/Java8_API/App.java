package com.locky.JAVA8_Lecture.Java8_API;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("lokcy");
        name.add("keesun");
        name.add("toby");
        name.add("foo");

        //forEach를 통해 컬렉션에 있는 인자값들을 순회할 수 있다.
        name.forEach((s)-> System.out.println(s));

        //Spliterator는 trySplit()메서드를 통해 컬렉션에 들어가 있는 인자들을 반으로 나눌 수 있다.
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while (spliterator.tryAdvance((s) -> System.out.println(s)));
        System.out.println("================================");
        while (spliterator1.tryAdvance((s) -> System.out.println(s)));


        //steam()을 통해서 인자값들을 순회하며 작업을 할 수 있다.
        //필터를 이용해 걸러서 갯수를 구해온다.
        name.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("l"))
                .count();

        //필터를 이용해 걸러서 List로 모아온다.
        name.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("l"))
                .collect(Collectors.toList());

    }
}
