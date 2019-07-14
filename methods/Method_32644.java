/** 
 * Converts this period to a period in days assuming a 7 day week, 24 hour day, 60 minute hour and 60 second minute. <p> This method allows you to convert between different types of period. However to achieve this it makes the assumption that all weeks are 7 days, all days are 24 hours, all hours are 60 minutes and all minutes are 60 seconds. This is not true when daylight savings time is considered, and may also not be true for some unusual chronologies. However, it is included as it is a useful operation for many applications and business rules. <p> If the period contains years or months, an exception will be thrown.
 * @return a period representing the number of standard days in this period
 * @throws UnsupportedOperationException if the period contains years or months
 * @throws ArithmeticException if the number of days is too large to be represented
 * @since 1.5
 */
public Days toStandardDays(){
  checkYearsAndMonths("Days");
  long millis=getMillis();
  millis+=((long)getSeconds()) * DateTimeConstants.MILLIS_PER_SECOND;
  millis+=((long)getMinutes()) * DateTimeConstants.MILLIS_PER_MINUTE;
  millis+=((long)getHours()) * DateTimeConstants.MILLIS_PER_HOUR;
  long days=millis / DateTimeConstants.MILLIS_PER_DAY;
  days=FieldUtils.safeAdd(days,getDays());
  days=FieldUtils.safeAdd(days,((long)getWeeks()) * ((long)DateTimeConstants.DAYS_PER_WEEK));
  return Days.days(FieldUtils.safeToInt(days));
}
