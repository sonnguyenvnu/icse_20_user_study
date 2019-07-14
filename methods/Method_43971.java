/** 
 * Convert java time to unix time long, simply by dividing by the time 1000 
 */
public static long toUnixTime(Date time){
  return time.getTime() / 1000;
}
