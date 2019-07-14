/** 
 * Allocate a bitmap that has a backing memory allocation of 'size' bytes. This is configuration agnostic so the size is the actual size in bytes of the bitmap.
 * @param size the 'size' in bytes of the bitmap
 * @return a new bitmap with the specified size in memory
 */
@Override protected Bitmap alloc(int size){
  return Bitmap.createBitmap(1,(int)Math.ceil(size / (double)BitmapUtil.RGB_565_BYTES_PER_PIXEL),Bitmap.Config.RGB_565);
}
