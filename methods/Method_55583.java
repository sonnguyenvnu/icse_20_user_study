/** 
 * Returns the stack of the current thread. 
 */
public static MemoryStack stackGet(){
  return TLS.get();
}
