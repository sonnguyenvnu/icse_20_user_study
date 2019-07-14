/** 
 * Thread-local version of  {@link #bytes(byte)}. 
 */
public static ByteBuffer stackBytes(byte... values){
  return stackGet().bytes(values);
}
