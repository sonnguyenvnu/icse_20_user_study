/** 
 * ??bitmap
 * @param is        ???
 * @param maxWidth  ????
 * @param maxHeight ????
 * @return bitmap
 */
public static Bitmap getBitmap(InputStream is,int maxWidth,int maxHeight){
  if (is == null) {
    return null;
  }
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inJustDecodeBounds=true;
  BitmapFactory.decodeStream(is,null,options);
  options.inSampleSize=calculateInSampleSize(options,maxWidth,maxHeight);
  options.inJustDecodeBounds=false;
  return BitmapFactory.decodeStream(is,null,options);
}
