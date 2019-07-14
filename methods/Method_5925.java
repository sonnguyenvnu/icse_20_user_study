/** 
 * Returns the current byte offset. Must only be called when the position is byte aligned.
 * @throws IllegalStateException If the position isn't byte aligned.
 */
public int getBytePosition(){
  Assertions.checkState(bitOffset == 0);
  return byteOffset;
}
