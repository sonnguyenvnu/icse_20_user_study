/** 
 * @param millis from 1970-01-01T00:00:00Z
 * @param year precalculated year of millis
 * @param month precalculated month of millis
 */
int getDayOfMonth(long millis,int year,int month){
  long dateMillis=getYearMillis(year);
  dateMillis+=getTotalMillisByYearMonth(year,month);
  return (int)((millis - dateMillis) / DateTimeConstants.MILLIS_PER_DAY) + 1;
}
