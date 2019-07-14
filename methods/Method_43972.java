/** 
 * Convert java time to unix time long, simply by dividing by the time 1000. Null safe 
 */
public static Long toUnixTimeNullSafe(Date time){
  return time == null ? null : time.getTime() / 1000;
}
