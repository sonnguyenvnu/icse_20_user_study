/** 
 * ??bitmap
 * @param data      ??
 * @param offset    ???
 * @param maxWidth  ????
 * @param maxHeight ????
 * @return bitmap
 */
public static Bitmap getBitmap(byte[] data,int offset,int maxWidth,int maxHeight){
  if (data.length == 0) {
    return null;
  }
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inJustDecodeBounds=true;
  BitmapFactory.decodeByteArray(data,offset,data.length,options);
  options.inSampleSize=calculateInSampleSize(options,maxWidth,maxHeight);
  options.inJustDecodeBounds=false;
  return BitmapFactory.decodeByteArray(data,offset,data.length,options);
}
