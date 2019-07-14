/** 
 * Thread-local version of  {@link #shorts(short)}. 
 */
public static ShortBuffer stackShorts(short x){
  return stackGet().shorts(x);
}
