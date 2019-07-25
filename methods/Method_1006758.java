/** 
 * Returns a Duration of  {@code count} seconds.
 */
public static Duration secs(long count){
  return new Duration(count,TimeUnit.SECONDS);
}
