/** 
 * Convert java time long to unix time long, simply by dividing by 1000 
 */
public static long toUnixTime(long javaTime){
  return javaTime / 1000;
}
