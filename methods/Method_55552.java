/** 
 * Single value version of  {@link #mallocShort}. 
 */
public ShortBuffer shorts(short x){
  return mallocShort(1).put(0,x);
}
