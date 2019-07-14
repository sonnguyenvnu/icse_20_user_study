/** 
 * Thread-local version of  {@link #floats(float)}. 
 */
public static FloatBuffer stackFloats(float x){
  return stackGet().floats(x);
}
