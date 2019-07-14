/** 
 * Gets the maximum number of days in the month specified by the instant.
 * @param instant  millis from 1970-01-01T00:00:00Z
 * @return the maximum number of days in the month
 */
int getDaysInMonthMax(long instant){
  int thisYear=getYear(instant);
  int thisMonth=getMonthOfYear(instant,thisYear);
  return getDaysInYearMonth(thisYear,thisMonth);
}
