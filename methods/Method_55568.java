/** 
 * Three value version of  {@link #mallocFloat}. 
 */
public FloatBuffer floats(float x,float y,float z){
  return mallocFloat(3).put(0,x).put(1,y).put(2,z);
}
