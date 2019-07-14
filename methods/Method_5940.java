/** 
 * Reads the next  {@code length} bytes into {@code buffer}.
 * @see ByteBuffer#put(byte[],int,int)
 * @param buffer The {@link ByteBuffer} into which the read data should be written.
 * @param length The number of bytes to read.
 */
public void readBytes(ByteBuffer buffer,int length){
  buffer.put(data,position,length);
  position+=length;
}
