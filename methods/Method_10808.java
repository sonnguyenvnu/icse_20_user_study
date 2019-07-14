/** 
 * ??bitmap
 * @param res       ????
 * @param id        ??id
 * @param maxWidth  ????
 * @param maxHeight ????
 * @return bitmap
 */
public static Bitmap getBitmap(Resources res,int id,int maxWidth,int maxHeight){
  if (res == null) {
    return null;
  }
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inJustDecodeBounds=true;
  BitmapFactory.decodeResource(res,id,options);
  options.inSampleSize=calculateInSampleSize(options,maxWidth,maxHeight);
  options.inJustDecodeBounds=false;
  return BitmapFactory.decodeResource(res,id,options);
}
