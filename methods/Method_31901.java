/** 
 * @param instant millis from 1970-01-01T00:00:00Z
 * @param year precalculated year of millis
 */
int getWeekOfWeekyear(long instant,int year){
  long firstWeekMillis1=getFirstWeekOfYearMillis(year);
  if (instant < firstWeekMillis1) {
    return getWeeksInYear(year - 1);
  }
  long firstWeekMillis2=getFirstWeekOfYearMillis(year + 1);
  if (instant >= firstWeekMillis2) {
    return 1;
  }
  return (int)((instant - firstWeekMillis1) / DateTimeConstants.MILLIS_PER_WEEK) + 1;
}
