/** 
 * Thread-local version of  {@link #longs(long)}. 
 */
public static LongBuffer stackLongs(long x){
  return stackGet().longs(x);
}
