/** 
 * Thread-local version of  {@link #nmalloc(int)}. 
 */
public static long nstackMalloc(int size){
  return stackGet().nmalloc(size);
}
