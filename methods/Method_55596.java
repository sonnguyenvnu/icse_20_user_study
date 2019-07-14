/** 
 * Thread-local version of  {@link #ints(int)}. 
 */
public static IntBuffer stackInts(int... values){
  return stackGet().ints(values);
}
