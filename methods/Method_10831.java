/** 
 * ??bitmap
 * @param is ???
 * @return bitmap
 */
public Bitmap getBitmap(InputStream is){
  if (is == null) {
    return null;
  }
  return BitmapFactory.decodeStream(is);
}
