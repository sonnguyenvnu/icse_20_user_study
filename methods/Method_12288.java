/** 
 * Like  {@link #getAllocationByteCount()} but includes also backing {@link android.graphics.Bitmap} and takes sample size into account.
 * @param oldDrawable optional old drawable to be reused, pass {@code null} if there is no one
 * @param sampleSize  sample size, pass {@code 1} if not using subsampling
 * @return possible size of the memory needed to store pixels
 * @throws IllegalArgumentException if sample size out of range
 */
@Beta public long getDrawableAllocationByteCount(@Nullable GifDrawable oldDrawable,@IntRange(from=1,to=Character.MAX_VALUE) int sampleSize){
  if (sampleSize < 1 || sampleSize > Character.MAX_VALUE) {
    throw new IllegalStateException("Sample size " + sampleSize + " out of range <1, " + Character.MAX_VALUE + ">");
  }
  final int sampleSizeFactor=sampleSize * sampleSize;
  final long bufferSize;
  if (oldDrawable != null && !oldDrawable.mBuffer.isRecycled()) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      bufferSize=oldDrawable.mBuffer.getAllocationByteCount();
    }
 else {
      bufferSize=oldDrawable.getFrameByteCount();
    }
  }
 else {
    bufferSize=(mWidth * mHeight * 4) / sampleSizeFactor;
  }
  return (mPixelsBytesCount / sampleSizeFactor) + bufferSize;
}
