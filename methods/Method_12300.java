/** 
 * Returns size of the memory needed to store pixels of this object. It counts possible length of all frame buffers. Returned value may be lower than amount of actually allocated memory if GIF uses dispose to previous method but frame requiring it has never been needed yet. Returned value does not change during runtime.
 * @return possible size of the memory needed to store pixels of this object
 */
public long getAllocationByteCount(){
  long byteCount=mNativeInfoHandle.getAllocationByteCount();
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    byteCount+=mBuffer.getAllocationByteCount();
  }
 else {
    byteCount+=getFrameByteCount();
  }
  return byteCount;
}
