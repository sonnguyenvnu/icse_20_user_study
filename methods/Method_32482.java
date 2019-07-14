/** 
 * Correct <code>date</code> in case of DST overlap. <p> The <code>Date</code> object created has exactly the same fields as this date-time, except when the time would be invalid due to a daylight savings gap. In that case, the time will be set to the earliest valid time after the gap. <p> In the case of a daylight savings overlap, the earlier instant is selected. <p> Converting to a JDK Date is full of complications as the JDK Date constructor doesn't behave as you might expect around DST transitions. This method works by taking a first guess and then adjusting. This also handles the situation where the JDK time zone data differs from the Joda-Time time zone data.
 * @see #toDate()
 */
private Date correctDstTransition(Date date,final TimeZone timeZone){
  Calendar calendar=Calendar.getInstance(timeZone);
  calendar.setTime(date);
  LocalDateTime check=LocalDateTime.fromCalendarFields(calendar);
  if (check.isBefore(this)) {
    while (check.isBefore(this)) {
      calendar.setTimeInMillis(calendar.getTimeInMillis() + 60000);
      check=LocalDateTime.fromCalendarFields(calendar);
    }
    while (check.isBefore(this) == false) {
      calendar.setTimeInMillis(calendar.getTimeInMillis() - 1000);
      check=LocalDateTime.fromCalendarFields(calendar);
    }
    calendar.setTimeInMillis(calendar.getTimeInMillis() + 1000);
  }
 else   if (check.equals(this)) {
    final Calendar earlier=Calendar.getInstance(timeZone);
    earlier.setTimeInMillis(calendar.getTimeInMillis() - timeZone.getDSTSavings());
    check=LocalDateTime.fromCalendarFields(earlier);
    if (check.equals(this)) {
      calendar=earlier;
    }
  }
  return calendar.getTime();
}
