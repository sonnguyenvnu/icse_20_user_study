/** 
 * Single value version of  {@link #mallocInt}. 
 */
public IntBuffer ints(int x){
  return mallocInt(1).put(0,x);
}
