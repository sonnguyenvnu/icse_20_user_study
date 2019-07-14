/** 
 * Two value version of  {@link #mallocInt}. 
 */
public IntBuffer ints(int x,int y){
  return mallocInt(2).put(0,x).put(1,y);
}
