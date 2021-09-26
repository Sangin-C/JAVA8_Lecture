package com.locky.JAVA8_Lecture.Date;

import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateTimeKeyDeserializer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateAPI {

    public static void main(String[] args) {
        //기계용 API는 어떤 시간을 재거나 메소드 실행시간을 비교하거나 할때 쓰인다.
        System.out.println("기계용 API 호출");
        Instant instant = Instant.now();
        System.out.println(instant);    //기준시 UTC, GMT
        System.out.println("=============================================");

        System.out.println("현재 내가 있는 지역 나타내기");
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        System.out.println("=============================================");

        System.out.println("현재 내가 있는 지역의 시간 나타내기");
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
        System.out.println("=============================================");

        //기계용 시간 비교하기
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);

        System.out.println("기계용 시간 비교하기");
        Duration between = Duration.between(now, plus);
        System.out.println(between.getSeconds());
        System.out.println("=============================================");


        //사람용 API는 우리가 사용하는 날짜 형식을 사용할때 쓰인다.
        System.out.println("사람용 API 호출");

        //현재시간 나타내기
        System.out.println("현재 시간");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println("=============================================");

        //특정시간 나타내기
        System.out.println("특정 시간");
        LocalDateTime birthday = LocalDateTime.of(1993, Month.SEPTEMBER, 9, 3, 0, 30);
        System.out.println(birthday);
        System.out.println("=============================================");

        //특전 존의 시간 나타내기
        System.out.println("특정 존의 시간");
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);
        System.out.println("=============================================");

        //기간 나타내기
        System.out.println("기간");
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.JULY, 15);

        //기간 비교하기 ( 일수로 표현 )
        System.out.println("기간 비교하기 1");
        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());
        System.out.println("=============================================");

        System.out.println("기간 비교하기 2");
        Period until = today.until(thisYearBirthday);
        System.out.println(until.getDays());
        System.out.println("=============================================");


        //날짜 포맷 지정하기
        System.out.println("날짜 포맷팅");
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(localDateTime.format(MMddyyyy));
        System.out.println("=============================================");

        System.out.println("날짜 파싱");
        LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println(parse);

    }
}
