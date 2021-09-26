package com.locky.JAVA8_Lecture.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateApp {
    public static void main(String[] args) throws InterruptedException {

        //기존에는 이렇게 날짜 형식을 받아와서 사용했다.
        Date date = new Date();
        long time = date.getTime();
        System.out.println(date);
        System.out.println(time);

        //멀티 스레드환경에서 Date는 mutable하지 않다.
        //thread safe하지 않다!
        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        //이렇게 타임을 지정해 줄 수도 있다.
        after3Seconds.setTime(time);
        System.out.println(after3Seconds);


        //type safty하지 않다! 인자값으로 -10 이 올수도 있고, 아무 값이나 올수 있다.
        Calendar calendar = new GregorianCalendar(1982, Calendar.APRIL, 10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    }
}
