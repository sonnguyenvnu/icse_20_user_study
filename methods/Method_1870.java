/** 
 * @return size in bytes of the underlying bitmap
 */
@SuppressLint("NewApi") public static int getSizeInBytes(@Nullable Bitmap bitmap){
  if (bitmap == null) {
    return 0;
  }
  if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
    try {
      return bitmap.getAllocationByteCount();
    }
 catch (    NullPointerException npe) {
    }
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
    return bitmap.getByteCount();
  }
  return bitmap.getRowBytes() * bitmap.getHeight();
}
