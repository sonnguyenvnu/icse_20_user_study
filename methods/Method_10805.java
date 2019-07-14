/** 
 * ??bitmap
 * @param filePath  ????
 * @param maxWidth  ????
 * @param maxHeight ????
 * @return bitmap
 */
public static Bitmap getBitmap(String filePath,int maxWidth,int maxHeight){
  if (RxDataTool.isNullString(filePath)) {
    return null;
  }
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inJustDecodeBounds=true;
  BitmapFactory.decodeFile(filePath,options);
  options.inSampleSize=calculateInSampleSize(options,maxWidth,maxHeight);
  options.inJustDecodeBounds=false;
  return BitmapFactory.decodeFile(filePath,options);
}
