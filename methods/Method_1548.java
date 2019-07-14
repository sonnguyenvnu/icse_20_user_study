/** 
 * Includes given bitmap in the bitmap count. The bitmap is included only if doing so does not violate configured limit
 * @param bitmap to include in the count
 * @return true if and only if bitmap is successfully included in the count
 */
public synchronized boolean increase(Bitmap bitmap){
  final int bitmapSize=BitmapUtil.getSizeInBytes(bitmap);
  if (mCount >= mMaxCount || mSize + bitmapSize > mMaxSize) {
    return false;
  }
  mCount++;
  mSize+=bitmapSize;
  return true;
}
