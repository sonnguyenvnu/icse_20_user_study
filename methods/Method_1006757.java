/** 
 * Returns a Duration of  {@code count} microseconds.
 */
public static Duration microseconds(long count){
  return new Duration(count,TimeUnit.MICROSECONDS);
}
