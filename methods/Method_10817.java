/** 
 * ??alpha??
 * @param src     ???
 * @param recycle ????
 * @return alpha??
 */
public static Bitmap toAlpha(Bitmap src,Boolean recycle){
  if (isEmptyBitmap(src)) {
    return null;
  }
  Bitmap ret=src.extractAlpha();
  if (recycle && !src.isRecycled()) {
    src.recycle();
  }
  return ret;
}
