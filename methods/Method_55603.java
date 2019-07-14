/** 
 * Thread-local version of  {@link #floats(float)}. 
 */
public static FloatBuffer stackFloats(float... values){
  return stackGet().floats(values);
}
