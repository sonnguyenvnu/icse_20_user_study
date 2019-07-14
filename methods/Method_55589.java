/** 
 * Thread-local version of  {@link #bytes(byte,byte,byte,byte)}. 
 */
public static ByteBuffer stackBytes(byte x,byte y,byte z,byte w){
  return stackGet().bytes(x,y,z,w);
}
