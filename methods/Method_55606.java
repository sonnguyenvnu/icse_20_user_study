/** 
 * Thread-local version of  {@link #doubles(double,double)}. 
 */
public static DoubleBuffer stackDoubles(double x,double y){
  return stackGet().doubles(x,y);
}
