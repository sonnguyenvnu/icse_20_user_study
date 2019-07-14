/** 
 * Thread-local version of  {@link #longs(long)}. 
 */
public static LongBuffer stackLongs(long... values){
  return stackGet().longs(values);
}
