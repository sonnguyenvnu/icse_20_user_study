/** 
 * @param millis from 1970-01-01T00:00:00Z
 * @param year precalculated year of millis
 */
int getDayOfMonth(long millis,int year){
  int month=getMonthOfYear(millis,year);
  return getDayOfMonth(millis,year,month);
}
