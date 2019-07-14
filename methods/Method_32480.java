/** 
 * Get the date time as a <code>java.util.Date</code>. <p> The <code>Date</code> object created has exactly the same fields as this date-time, except when the time would be invalid due to a daylight savings gap. In that case, the time will be set to the earliest valid time after the gap. <p> In the case of a daylight savings overlap, the earlier instant is selected. <p> Converting to a JDK Date is full of complications as the JDK Date constructor doesn't behave as you might expect around DST transitions. This method works by taking a first guess and then adjusting. This also handles the situation where the JDK time zone data differs from the Joda-Time time zone data.
 * @return a Date initialised with this date-time, never null
 * @since 2.0
 */
@SuppressWarnings("deprecation") public Date toDate(){
  int dom=getDayOfMonth();
  Date date=new Date(getYear() - 1900,getMonthOfYear() - 1,dom,getHourOfDay(),getMinuteOfHour(),getSecondOfMinute());
  date.setTime(date.getTime() + getMillisOfSecond());
  return correctDstTransition(date,TimeZone.getDefault());
}
