package com.locky.JAVA8_Lecture.Interface_method;

public class MethodApp {
    public static void main(String[] args) {
        Basic_method basic_method = new Default_method("locky");

        basic_method.printName();
        basic_method.printNameUpperCase();

        Basic_method.printAnything();
    }
}
