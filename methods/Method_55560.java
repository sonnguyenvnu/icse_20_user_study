/** 
 * Single value version of  {@link #mallocLong}. 
 */
public LongBuffer longs(long x){
  return mallocLong(1).put(0,x);
}
