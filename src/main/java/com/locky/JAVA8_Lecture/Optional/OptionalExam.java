package com.locky.JAVA8_Lecture.Optional;

import com.locky.JAVA8_Lecture.Stream.OnlineClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalExam {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        //.isPresent() 값이 있는지 체크한다.
        System.out.println("값이 있는지 체크한다.");
        boolean present = optional.isPresent();
        System.out.println(present);
        System.out.println("======================================");

        //.isEmpty() 값이 비어있는지 확인한다. (JAVA11 부터 제공)
        System.out.println("값이 비어있는지 확인한다.");
        boolean empty = optional.isEmpty();
        System.out.println(empty);
        System.out.println("======================================");

        //.get()을 하면 Optional에 있는 값을 꺼내올 수 있다.
        //값이 있으면 문제가 없지만, 값이 없는걸 꺼내려고하면 Runtime Exception이 터진다.
        //Optional값이 있는지 확인하고 써야한다. if문보다는 Optional이 제공해주는 API를 사용하는게 좋다.
        System.out.println("Optional에 있는 값 가져오기");
        if (optional.isPresent()) {
            OnlineClass onlineClass = optional.get();
            System.out.println(onlineClass.getTitle());
            System.out.println("======================================");
        }

        //.ifPresent()는 값이 있는 경우에 그 값을 처리(가공)할 수 있다.
        System.out.println("Optional에 값이 있는 경우에 그 값을 가지고 ~~를 하라");
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));
        System.out.println("======================================");


        //.orElse()는 값이 있으면 가져오고 없으면 어떤 행위를 할 수있다.
        //.orElse()는 값이 있어도 파라미터를 실행하고, 없어도 파라미터를 실행한다.
        System.out.println("Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라 /  있어도 파라미터를 실행하고, 없어도 파라미터를 실행한다.");
        OnlineClass onlineClass = optional.orElse(createNewJpaClass());
        System.out.println(onlineClass.getTitle());
        System.out.println("======================================");

        //.orElseGet()은 값이 없을때만 파라미터를 실행한다.
        System.out.println("Optional에 값이 있으면 가져오고 없는 경우에 ~~를 하라 / 없을때만 파라미터를 실행한다.");
        OnlineClass onlineClassGet = optional.orElseGet(() ->createNewJpaClass());
        System.out.println(onlineClassGet.getTitle());
        System.out.println("======================================");

        //.orElseThrow()은 값이 없으면 Exception을 던진다.
        //원하는 에러가 있으면 작성하면된다.
        System.out.println("Optional에 값이 있으면 가졍고 없는 경우 에러를 던져라.");
        OnlineClass onlineClassThrow = optional.orElseThrow(() -> {
            return new IllegalArgumentException();
        });
        System.out.println(onlineClassThrow.getTitle());
        System.out.println("======================================");

        //.filter()는 값이 있다는 가정하에 쓰는것이다. 값이 없으면 아무것도 리턴해주지 않는다.
        System.out.println("Optional에 들어있는 값 걸러내기");
        Optional<OnlineClass> onlineClassFilter = optional.filter(oc -> !oc.isClosed());
        System.out.println(onlineClassFilter.isEmpty());
        System.out.println("======================================");

        //.map()은 값을 변환할때 쓰인다.
        System.out.println("Optional에 들어있는 값 변환하기");
        Optional<Integer> integer = optional.map(oc -> {
            return oc.getId();
        });
        System.out.println(integer.isPresent());

    }

    private static OnlineClass createNewJpaClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New class", true);
    }
}
