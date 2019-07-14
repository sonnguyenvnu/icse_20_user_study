/** 
 * Checks the specified values for validity as a set of time values.
 * @param hourOfDay the hour of the time to check (in military (24-hour)time)
 * @param minute    the minute of the time to check
 * @param second    the second of the time to check
 * @param millis    the millisecond of the time to check
 */
private void validate(int hourOfDay,int minute,int second,int millis){
  if (hourOfDay < 0 || hourOfDay > 23) {
    throw new IllegalArgumentException(invalidHourOfDay + hourOfDay);
  }
  if (minute < 0 || minute > 59) {
    throw new IllegalArgumentException(invalidMinute + minute);
  }
  if (second < 0 || second > 59) {
    throw new IllegalArgumentException(invalidSecond + second);
  }
  if (millis < 0 || millis > 999) {
    throw new IllegalArgumentException(invalidMillis + millis);
  }
}
