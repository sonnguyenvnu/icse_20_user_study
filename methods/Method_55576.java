/** 
 * Single value version of  {@link #mallocPointer}. 
 */
public PointerBuffer pointers(Buffer x){
  return mallocPointer(1).put(0,memAddress(x));
}
