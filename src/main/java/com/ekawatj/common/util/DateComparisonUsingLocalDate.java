package com.ekawatj.java.common.util;

/**
 * Created by ejirapongpan
 */
import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateComparisonUsingLocalDate {


  LocalDate now =  LocalDate.now();
  String beginningOfMonth = now.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
  String today = now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
  String yesterday = now.minusDays(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

  void checkIfSameDay() throws Exception {
    String startDateStr = "03/03/2011";
    String endDateStr = "03/03/2011";
    Date endDate = formatter.parse(endDateStr);

    boolean isSameDay;
    isSameDay = DateUtils.isSameDay(formatter.parse(beginningOfMonth), formatter.parse(startDateStr));
    System.out.println("==1=="+isSameDay);
    isSameDay = DateUtils.isSameDay(formatter.parse(yesterday), endDate);
    System.out.println("==2=="+isSameDay);

    isSameDay = DateUtils.isSameDay(formatter.parse(yesterday), formatter.parse(startDateStr));
    System.out.println("==3=="+isSameDay);
    isSameDay = DateUtils.isSameDay(formatter.parse(yesterday), endDate);
    System.out.println("==4=="+isSameDay);
  }

}
