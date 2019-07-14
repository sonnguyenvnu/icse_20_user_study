/** 
 * ??bitmap
 * @param data   ??
 * @param offset ???
 * @return bitmap
 */
public Bitmap getBitmap(byte[] data,int offset){
  if (data.length == 0) {
    return null;
  }
  return BitmapFactory.decodeByteArray(data,offset,data.length);
}
