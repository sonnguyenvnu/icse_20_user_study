/** 
 * ??????????????,????? (start, end)???0:00,?start < end??
 * @param time
 * @param start
 * @param end
 * @return
 */
public static boolean isInTimeArea(int[] time,int[] start,int[] end){
  if (fomerIsEqualOrBigger(end,start)) {
    return fomerIsEqualOrBigger(time,start) && fomerIsEqualOrBigger(end,time);
  }
  if (fomerIsEqualOrBigger(time,start) && fomerIsEqualOrBigger(MAX_TIME_DETAILS,time)) {
    return true;
  }
  if (fomerIsEqualOrBigger(time,MIN_TIME_DETAILS) && fomerIsEqualOrBigger(end,time)) {
    return true;
  }
  return false;
}
