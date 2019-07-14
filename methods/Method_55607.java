/** 
 * Thread-local version of  {@link #doubles(double)}. 
 */
public static DoubleBuffer stackDoubles(double... values){
  return stackGet().doubles(values);
}
