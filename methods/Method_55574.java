/** 
 * Four value version of  {@link #mallocDouble}. 
 */
public DoubleBuffer doubles(double x,double y,double z,double w){
  return mallocDouble(4).put(0,x).put(1,y).put(2,z).put(3,w);
}
