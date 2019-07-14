/** 
 * Vararg version of  {@link #malloc}. 
 */
public ByteBuffer bytes(byte... values){
  ByteBuffer buffer=malloc(1,values.length).put(values);
  buffer.flip();
  return buffer;
}
