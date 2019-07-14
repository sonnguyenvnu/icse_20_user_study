/** 
 * Two value version of  {@link #mallocFloat}. 
 */
public FloatBuffer floats(float x,float y){
  return mallocFloat(2).put(0,x).put(1,y);
}
