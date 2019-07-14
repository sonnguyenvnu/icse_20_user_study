/** 
 * Three value version of  {@link #mallocLong}. 
 */
public LongBuffer longs(long x,long y,long z){
  return mallocLong(3).put(0,x).put(1,y).put(2,z);
}
