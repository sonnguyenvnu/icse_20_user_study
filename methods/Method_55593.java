/** 
 * Thread-local version of  {@link #shorts(short)}. 
 */
public static ShortBuffer stackShorts(short... values){
  return stackGet().shorts(values);
}
