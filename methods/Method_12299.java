/** 
 * Returns the minimum number of bytes that can be used to store pixels of the single frame. Returned value is the same for all the frames since it is based on the size of GIF screen. <p>This method should not be used to calculate the memory usage of the bitmap. Instead see  {@link #getAllocationByteCount()}.
 * @return the minimum number of bytes that can be used to store pixels of the single frame
 */
public int getFrameByteCount(){
  return mBuffer.getRowBytes() * mBuffer.getHeight();
}
