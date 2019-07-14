/** 
 * Thread-local version of  {@link #pointers(Pointer,Pointer)}. 
 */
public static PointerBuffer stackPointers(Pointer x,Pointer y){
  return stackGet().pointers(x,y);
}
