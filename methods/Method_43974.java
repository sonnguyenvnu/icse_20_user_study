/** 
 * Convert unix time to Java Date 
 */
public static Date fromUnixTime(long unix){
  return new Date(unix * 1000);
}
