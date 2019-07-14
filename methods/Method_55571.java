/** 
 * Single value version of  {@link #mallocDouble}. 
 */
public DoubleBuffer doubles(double x){
  return mallocDouble(1).put(0,x);
}
