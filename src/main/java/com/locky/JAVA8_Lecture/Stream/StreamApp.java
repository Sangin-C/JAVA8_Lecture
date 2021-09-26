package com.locky.JAVA8_Lecture.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StreamApp {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("lokcy");
        names.add("keesun");
        names.add("toby");
        names.add("foo");

        //Stream
        //데이터를 담고 있는 저장소(컬렉션)가 아니다.
        //Stream이 처리하는 데이터 소스를 변경하지 않는다.
        //Stream으로 처리하는 데이터는 오직 한번만 처리한다.
        //중개 오퍼레이션은 근본적으로 lazy하다.
        //중개 오퍼레이션 - filter, map, limit, skip, sorted, .... ( 스트림을 리턴한다 )
        //종료 오퍼레이션 - collect, allMatch, count, forEach, min, max, .... ( 스트림을 리턴하지 않는다 )

        //스트림 파이프라인에는 0 개 혹은 여러개의 중개오퍼레이션과 단 하나의 종료 오퍼레이션으로 구성된다.

        //종료형 오퍼레이션이 오기 전까지 중개형 오퍼레이션은 실행되지 않는다.

        //이렇게 .map()으로 끝나면 map()은 중개형 오퍼레이션이기 때문에 실행되지 않는다.
        names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        System.out.println("====================================");

        //.collect()는 종료형 오퍼레이터이기 때문에 중개형 오퍼레이터 즉, .map()이 실행이 된다.
        List<String> collect = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        System.out.println("====================================");

        collect.forEach(System.out::println);

        System.out.println("====================================");

        //단순 for문으로 사용하면 되지 왜 stream을 쓰냐??
        //루프를 돌면서 엘리먼트들을 병렬적으로 처리하기가 어렵기 때문이다.
        //stream을 사용하면 벙렬처리하기가 쉽다.
        for (String name : names) {
            if (name.startsWith("l")) {
                System.out.println(name.toUpperCase(Locale.ROOT));
            }
        }

        System.out.println("====================================");

        //parallelStream()을 사용하면 JVM이 알아서 병렬적으로 처리해준다.
        //무조건 병렬처리로 사용하는것이 좋은것은 아니다.
        //parallelStream()은 여러 스레드에 나눠서 처리한다.
        //그냥 stream()은 하나의 스레드에서 처리가된다.
        List<String> collect1 = names.parallelStream().map(String::toUpperCase)
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);
    }

}
