/** 
 * Two value version of  {@link #mallocLong}. 
 */
public LongBuffer longs(long x,long y){
  return mallocLong(2).put(0,x).put(1,y);
}
