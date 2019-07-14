/** 
 * Get the date time as a <code>java.util.Date</code> using the specified time zone. <p> The <code>Date</code> object created has exactly the same fields as this date-time, except when the time would be invalid due to a daylight savings gap. In that case, the time will be set to the earliest valid time after the gap. <p> In the case of a daylight savings overlap, the earlier instant is selected. <p> Converting to a JDK Date is full of complications as the JDK Date constructor doesn't behave as you might expect around DST transitions. This method works by taking a first guess and then adjusting. This also handles the situation where the JDK time zone data differs from the Joda-Time time zone data. <p> Unlike  {@link #toDate()}, this implementation does not rely on Java's synchronized time zone initialization logic, and should demonstrate better concurrent performance characteristics.
 * @return a Date initialised with this date-time, never null
 * @since 2.3
 */
public Date toDate(final TimeZone timeZone){
  final Calendar calendar=Calendar.getInstance(timeZone);
  calendar.clear();
  calendar.set(getYear(),getMonthOfYear() - 1,getDayOfMonth(),getHourOfDay(),getMinuteOfHour(),getSecondOfMinute());
  Date date=calendar.getTime();
  date.setTime(date.getTime() + getMillisOfSecond());
  return correctDstTransition(date,timeZone);
}
