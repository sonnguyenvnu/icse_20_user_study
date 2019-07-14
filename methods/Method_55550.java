/** 
 * Single value version of  {@link #malloc}. 
 */
public ByteBuffer bytes(byte x){
  return malloc(1,1).put(0,x);
}
