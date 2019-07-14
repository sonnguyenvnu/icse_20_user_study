/** 
 * Thread-local version of  {@link #floats(float,float,float,float)}. 
 */
public static FloatBuffer stackFloats(float x,float y,float z,float w){
  return stackGet().floats(x,y,z,w);
}
