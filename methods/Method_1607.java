/** 
 * Pin the bitmap so that it cannot be 'purged'. Only makes sense for purgeable bitmaps WARNING: Use with caution. Make sure that the pinned bitmap is recycled eventually. Otherwise, this will simply eat up ashmem memory and eventually lead to unfortunate crashes. We *may* eventually provide an unpin method - but we don't yet have a compelling use case for that.
 * @param bitmap the purgeable bitmap to pin
 */
public CloseableReference<Bitmap> pinBitmap(Bitmap bitmap){
  Preconditions.checkNotNull(bitmap);
  try {
    nativePinBitmap(bitmap);
  }
 catch (  Exception e) {
    bitmap.recycle();
    throw Throwables.propagate(e);
  }
  if (!mUnpooledBitmapsCounter.increase(bitmap)) {
    int bitmapSize=BitmapUtil.getSizeInBytes(bitmap);
    bitmap.recycle();
    String detailMessage=String.format(Locale.US,"Attempted to pin a bitmap of size %d bytes." + " The current pool count is %d, the current pool size is %d bytes." + " The current pool max count is %d, the current pool max size is %d bytes.",bitmapSize,mUnpooledBitmapsCounter.getCount(),mUnpooledBitmapsCounter.getSize(),mUnpooledBitmapsCounter.getMaxCount(),mUnpooledBitmapsCounter.getMaxSize());
    throw new TooManyBitmapsException(detailMessage);
  }
  return CloseableReference.of(bitmap,mUnpooledBitmapsCounter.getReleaser());
}
