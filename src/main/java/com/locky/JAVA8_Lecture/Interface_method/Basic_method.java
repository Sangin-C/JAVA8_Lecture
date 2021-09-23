package com.locky.JAVA8_Lecture.Interface_method;

import java.util.Locale;

public interface Basic_method {

    void printName();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     * 기본 메서드를 통해 이 인터페이스를 구현하는 모든 구현체들에게 추가적인 메서드를 제공할 수 있다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    /**
     * @implSpec static 메서드를 제공할 수 있다.
     */
    static void printAnything() {
        System.out.println("Locky");
    }

    String getName();
}
