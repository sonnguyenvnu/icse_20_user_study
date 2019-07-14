/** 
 * Ensures that  {@link #data} is large enough to accommodate a write of a given length at itscurrent position. <p>If the capacity of  {@link #data} is sufficient this method does nothing. If the capacity isinsufficient then an attempt is made to replace  {@link #data} with a new {@link ByteBuffer}whose capacity is sufficient. Data up to the current position is copied to the new buffer.
 * @param length The length of the write that must be accommodated, in bytes.
 * @throws IllegalStateException If there is insufficient capacity to accommodate the write andthe buffer replacement mode of the holder is  {@link #BUFFER_REPLACEMENT_MODE_DISABLED}.
 */
public void ensureSpaceForWrite(int length){
  if (data == null) {
    data=createReplacementByteBuffer(length);
    return;
  }
  int capacity=data.capacity();
  int position=data.position();
  int requiredCapacity=position + length;
  if (capacity >= requiredCapacity) {
    return;
  }
  ByteBuffer newData=createReplacementByteBuffer(requiredCapacity);
  if (position > 0) {
    data.position(0);
    data.limit(position);
    newData.put(data);
  }
  data=newData;
}
