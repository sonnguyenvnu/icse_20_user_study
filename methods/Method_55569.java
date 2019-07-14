/** 
 * Four value version of  {@link #mallocFloat}. 
 */
public FloatBuffer floats(float x,float y,float z,float w){
  return mallocFloat(4).put(0,x).put(1,y).put(2,z).put(3,w);
}
