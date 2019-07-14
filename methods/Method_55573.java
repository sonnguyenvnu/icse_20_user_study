/** 
 * Three value version of  {@link #mallocDouble}. 
 */
public DoubleBuffer doubles(double x,double y,double z){
  return mallocDouble(3).put(0,x).put(1,y).put(2,z);
}
