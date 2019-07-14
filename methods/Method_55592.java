/** 
 * Thread-local version of  {@link #shorts(short,short)}. 
 */
public static ShortBuffer stackShorts(short x,short y){
  return stackGet().shorts(x,y);
}
