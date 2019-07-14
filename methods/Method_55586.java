/** 
 * Thread-local version of  {@link #bytes(byte)}. 
 */
public static ByteBuffer stackBytes(byte x){
  return stackGet().bytes(x);
}
