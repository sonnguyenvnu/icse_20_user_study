/** 
 * Three value version of  {@link #mallocInt}. 
 */
public IntBuffer ints(int x,int y,int z){
  return mallocInt(3).put(0,x).put(1,y).put(2,z);
}
