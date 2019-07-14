/** 
 * Thread-local version of  {@link #bytes(byte,byte)}. 
 */
public static ByteBuffer stackBytes(byte x,byte y){
  return stackGet().bytes(x,y);
}
