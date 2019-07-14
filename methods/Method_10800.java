/** 
 * drawable?byteArr
 * @param drawable drawable??
 * @param format   ??
 * @return ????
 */
public static byte[] drawable2Bytes(Drawable drawable,CompressFormat format){
  Bitmap bitmap=drawable2Bitmap(drawable);
  return bitmap2Bytes(bitmap,format);
}
