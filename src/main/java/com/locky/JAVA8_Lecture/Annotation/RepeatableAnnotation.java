package com.locky.JAVA8_Lecture.Annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //이 애노테이션을 사용한 곳에서 이 애노테이션의 정보를 언제까지 유지할것인가에 대한 정의이다.
@Target(ElementType.TYPE_USE) //TYPE_PARAMETER - 제네릭한 타입 파라미터에 사용할 수 있도록 타겟을 지정
@Repeatable(RepetableContainer.class)
public @interface RepeatableAnnotation {
    String value();
}
