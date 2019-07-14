/** 
 * Thread-local version of  {@link #floats(float,float,float)}. 
 */
public static FloatBuffer stackFloats(float x,float y,float z){
  return stackGet().floats(x,y,z);
}
