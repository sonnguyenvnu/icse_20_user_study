/** 
 * Get the milliseconds for a particular date.
 * @param year The year to use.
 * @param month The month to use
 * @param dayOfMonth The day of the month to use
 * @return millis from 1970-01-01T00:00:00Z
 */
long getYearMonthDayMillis(int year,int month,int dayOfMonth){
  long millis=getYearMillis(year);
  millis+=getTotalMillisByYearMonth(year,month);
  return millis + (dayOfMonth - 1) * (long)DateTimeConstants.MILLIS_PER_DAY;
}
