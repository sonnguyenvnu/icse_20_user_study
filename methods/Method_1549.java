/** 
 * Excludes given bitmap from the count.
 * @param bitmap to be excluded from the count
 */
public synchronized void decrease(Bitmap bitmap){
  final int bitmapSize=BitmapUtil.getSizeInBytes(bitmap);
  Preconditions.checkArgument(mCount > 0,"No bitmaps registered.");
  Preconditions.checkArgument(bitmapSize <= mSize,"Bitmap size bigger than the total registered size: %d, %d",bitmapSize,mSize);
  mSize-=bitmapSize;
  mCount--;
}
