/** 
 * Converts this period to a period in hours assuming a 7 day week, 24 hour day, 60 minute hour and 60 second minute. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all weeks are 7 days, all days are 24 hours, all hours are 60 minutes and all minutes are 60 seconds. This is not true when daylight savings time is considered, and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules. <p> If the period contains years or months, an exception will be thrown.
 * @return a period representing the number of standard hours in this period
 * @throws UnsupportedOperationException if the period contains years or months
 * @throws ArithmeticException if the number of hours is too large to be represented
 * @since 1.5
 */
public Hours toStandardHours(){
  checkYearsAndMonths("Hours");
  long millis=getMillis();
  millis+=((long)getSeconds()) * DateTimeConstants.MILLIS_PER_SECOND;
  millis+=((long)getMinutes()) * DateTimeConstants.MILLIS_PER_MINUTE;
  long hours=millis / DateTimeConstants.MILLIS_PER_HOUR;
  hours=FieldUtils.safeAdd(hours,getHours());
  hours=FieldUtils.safeAdd(hours,((long)getDays()) * ((long)DateTimeConstants.HOURS_PER_DAY));
  hours=FieldUtils.safeAdd(hours,((long)getWeeks()) * ((long)DateTimeConstants.HOURS_PER_WEEK));
  return Hours.hours(FieldUtils.safeToInt(hours));
}
