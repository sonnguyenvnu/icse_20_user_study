/** 
 * Get the number of weeks in the year.
 * @param year  the year to use
 * @return number of weeks in the year
 */
int getWeeksInYear(int year){
  long firstWeekMillis1=getFirstWeekOfYearMillis(year);
  long firstWeekMillis2=getFirstWeekOfYearMillis(year + 1);
  return (int)((firstWeekMillis2 - firstWeekMillis1) / DateTimeConstants.MILLIS_PER_WEEK);
}
