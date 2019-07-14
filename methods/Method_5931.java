/** 
 * Skips the next  {@code length} bytes. Must only be called when the position is byte aligned.
 * @param length The number of bytes to read.
 * @throws IllegalStateException If the position isn't byte aligned.
 */
public void skipBytes(int length){
  Assertions.checkState(bitOffset == 0);
  byteOffset+=length;
  assertValidOffset();
}
