/** 
 * @param instant millis from 1970-01-01T00:00:00Z
 */
int getWeekOfWeekyear(long instant){
  return getWeekOfWeekyear(instant,getYear(instant));
}
