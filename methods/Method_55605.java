/** 
 * Thread-local version of  {@link #doubles(double)}. 
 */
public static DoubleBuffer stackDoubles(double x){
  return stackGet().doubles(x);
}
