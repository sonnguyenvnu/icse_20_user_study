/** 
 * Thread-local version of  {@link #pointers(long)}. 
 */
public static PointerBuffer stackPointers(long x){
  return stackGet().pointers(x);
}
