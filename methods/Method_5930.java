/** 
 * Reads the next  {@code length} bytes into {@code buffer}. Must only be called when the position is byte aligned.
 * @see System#arraycopy(Object,int,Object,int,int)
 * @param buffer The array into which the read data should be written.
 * @param offset The offset in {@code buffer} at which the read data should be written.
 * @param length The number of bytes to read.
 * @throws IllegalStateException If the position isn't byte aligned.
 */
public void readBytes(byte[] buffer,int offset,int length){
  Assertions.checkState(bitOffset == 0);
  System.arraycopy(data,byteOffset,buffer,offset,length);
  byteOffset+=length;
  assertValidOffset();
}
