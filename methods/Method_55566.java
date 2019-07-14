/** 
 * Single value version of  {@link #mallocFloat}. 
 */
public FloatBuffer floats(float x){
  return mallocFloat(1).put(0,x);
}
