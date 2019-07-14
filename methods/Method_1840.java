/** 
 * Returns true if the image is a JPEG and its data is already complete at the specified length, false otherwise.
 */
public boolean isCompleteAt(int length){
  if (mImageFormat != DefaultImageFormats.JPEG) {
    return true;
  }
  if (mInputStreamSupplier != null) {
    return true;
  }
  Preconditions.checkNotNull(mPooledByteBufferRef);
  PooledByteBuffer buf=mPooledByteBufferRef.get();
  return (buf.read(length - 2) == (byte)JfifUtil.MARKER_FIRST_BYTE) && (buf.read(length - 1) == (byte)JfifUtil.MARKER_EOI);
}
