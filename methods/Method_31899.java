/** 
 * @param instant millis from 1970-01-01T00:00:00Z
 */
int getWeekyear(long instant){
  int year=getYear(instant);
  int week=getWeekOfWeekyear(instant,year);
  if (week == 1) {
    return getYear(instant + DateTimeConstants.MILLIS_PER_WEEK);
  }
 else   if (week > 51) {
    return getYear(instant - (2 * DateTimeConstants.MILLIS_PER_WEEK));
  }
 else {
    return year;
  }
}
