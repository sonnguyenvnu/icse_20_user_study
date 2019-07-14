/** 
 * Thread-local version of  {@link #callocPointer}. 
 */
public static PointerBuffer stackCallocPointer(int size){
  return stackGet().callocPointer(size);
}
