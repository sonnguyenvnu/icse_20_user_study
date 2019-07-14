/** 
 * Thread-local version of  {@link #ints(int)}. 
 */
public static IntBuffer stackInts(int x){
  return stackGet().ints(x);
}
