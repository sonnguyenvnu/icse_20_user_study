/** 
 * Thread-local version of  {@link #pointers(long)}. 
 */
public static PointerBuffer stackPointers(long... values){
  return stackGet().pointers(values);
}
