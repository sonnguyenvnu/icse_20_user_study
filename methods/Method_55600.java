/** 
 * Thread-local version of  {@link #floats(float,float)}. 
 */
public static FloatBuffer stackFloats(float x,float y){
  return stackGet().floats(x,y);
}
