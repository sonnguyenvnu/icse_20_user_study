/** 
 * Four value version of  {@link #mallocLong}. 
 */
public LongBuffer longs(long x,long y,long z,long w){
  return mallocLong(4).put(0,x).put(1,y).put(2,z).put(3,w);
}
