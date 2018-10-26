package com.techmango.common.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class DateUtilCheckMain
{
  public DateUtilCheckMain() {}
  
  public static void main(String[] args)
    throws ParseException
  {
    System.out.println("Convert String to ISO date : " + DateConversionUtil.checkconvertStringToISODate("10-01-2018"));
    
    System.out.println("Check given date with in date range : " + DateUtil.isWithinRange(new Date("10/24/2018"), new Date("10/10/2018"), new Date("10/25/2018")));
    
    System.out.println("Add days to Local date : " + DateUtil.addDaysToUtilDate(new Date(), 3));
    
    System.out.println("Add days to date : " + DateUtil.addDaysToLocalDate(LocalDate.now(), 4));
    
    System.out.println("Is a valid date : " + DateUtil.isThisDateValid("2018-10-01", "yyyy-MM-dd"));
    
    System.out.println("End of the day with time : " + DateUtil.getEndOfDay(new Date()));
    
    System.out.println("Start of the day with time : " + DateUtil.getStartOfDay(new Date()));
    
    System.out.println("Format date with datformat : " + DateConversionUtil.convertDateToStringFormat(new Date(), "MM/dd/yyyy"));
    
    System.out.println("Format date with datformat : " + DateConversionUtil.convertDateToStringFormat(new Date(), "yyyy-MM-dd"));
    
    System.out.println("Format date with datformat : " + DateConversionUtil.convertDateToStringFormat(new Date(), "MM-dd-yyyy"));
    
    System.out.println("In-between years exclude start/end dates : " + DateUtil.getInBetweenYearsExcludeStartAndEnd(2010, 2020));
    
    System.out.println("In-between years include start/end dates : " + DateUtil.getInBetweenYearsIncludeStartAndEnd(2010, 2020));
    
    System.out.println("get months for year : " + DateUtil.getMonthsForYear(2020));
    
    System.out.println("Get Dates of month with year : " + DateUtil.getDatesOfMonthWithYear(2018, 2));
    
    System.out.println("Get Quarter months : " + DateUtil.getQuartersForYear(2010));
    
    System.out.println("Get Index of short month : " + DateUtil.getIndexOfShortMonth("Jan"));
    
    System.out.println("first and last day of year : " + DateUtil.getFirstAndLastDateOfYear(2010));
    
    System.out.println("first and last date of month : " + DateUtil.getFirstAndLastDateOfMonth(2010, 1));
    
    System.out.println("week num with year and mon : " + DateUtil.getWeekNumberForMonth(2010, 1));
    
    System.out.println("hours per day : " + DateUtil.getHoursPerDay());
    
    System.out.println("dur : " + DateUtil.getDurationsByLimit(1, 5));
  }
}
