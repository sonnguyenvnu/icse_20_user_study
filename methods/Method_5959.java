/** 
 * Resets the wrapped data, limit and offset.
 * @param data The data to wrap.
 * @param offset The byte offset in {@code data} to start reading from.
 * @param limit The byte offset of the end of the bitstream in {@code data}.
 */
public void reset(byte[] data,int offset,int limit){
  this.data=data;
  byteOffset=offset;
  byteLimit=limit;
  bitOffset=0;
  assertValidOffset();
}
