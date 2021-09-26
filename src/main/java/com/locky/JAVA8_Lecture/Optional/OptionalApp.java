package com.locky.JAVA8_Lecture.Optional;

import com.locky.JAVA8_Lecture.Stream.OnlineClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OptionalApp {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);

        //기존에는 null 체크를 이런 식으로 해왔다. 개발자가 일일이 이렇게 체크를해줘야하는데 당연히 까먹을수 있고 실수할 수 있다.
        //그래서 Optional이라는것이 나온것임.
        Progress progress = spring_boot.getProgress();
        if (progress != null) {
            System.out.println(progress.getStudyDuration());
        }


    }



}
