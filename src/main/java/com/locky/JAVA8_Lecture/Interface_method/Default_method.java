package com.locky.JAVA8_Lecture.Interface_method;

import java.util.Locale;

public class Default_method implements Basic_method{

    String name;

    public Default_method(String name) {
        this.name = name;
    }

    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase());
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
