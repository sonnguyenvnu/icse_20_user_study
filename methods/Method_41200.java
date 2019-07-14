/** 
 * <p> Return true, if day is defined to be excluded. </p>
 * @param day The day of the month (from 1 to 31) to check.
 */
public boolean isDayExcluded(int day){
  if ((day < 1) || (day > MAX_DAYS_IN_MONTH)) {
    throw new IllegalArgumentException("The day parameter must be in the range of 1 to " + MAX_DAYS_IN_MONTH);
  }
  return excludeDays[day - 1];
}
