package com.locky.JAVA8_Lecture.Annotation;

import java.util.Arrays;
import java.util.List;

@RepeatableAnnotation("테스트1")
@RepeatableAnnotation("테스트2")
public class AnnotationApp {

    public static void main(String[] args) {
        //애노테이션을 가져다 쓰는 방법 1
        RepeatableAnnotation[] annotationsByType = AnnotationApp.class.getAnnotationsByType(RepeatableAnnotation.class);
        Arrays.stream(annotationsByType).forEach(a -> {
            System.out.println(a.value());
        });

        //애노테이션을 가져다 쓰는 방법 2 (컨테이너 사용)
        RepetableContainer annotation = AnnotationApp.class.getAnnotation(RepetableContainer.class);
        Arrays.stream(annotation.value()).forEach(a -> {
            System.out.println(a.value());
        });
    }

    static class Type_Parameter<@TypeParameter T> {

    }

    @TypeUse
    static class Type_Use<@TypeUse T>{

        public static <@TypeUse C> void test(@TypeUse C c) throws @TypeUse Exception{
            List<@TypeUse String> list = Arrays.asList("test");
        }

    }
}
