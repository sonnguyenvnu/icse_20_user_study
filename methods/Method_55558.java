/** 
 * Four value version of  {@link #mallocInt}. 
 */
public IntBuffer ints(int x,int y,int z,int w){
  return mallocInt(4).put(0,x).put(1,y).put(2,z).put(3,w);
}
