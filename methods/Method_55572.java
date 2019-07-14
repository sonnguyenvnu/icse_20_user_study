/** 
 * Two value version of  {@link #mallocDouble}. 
 */
public DoubleBuffer doubles(double x,double y){
  return mallocDouble(2).put(0,x).put(1,y);
}
