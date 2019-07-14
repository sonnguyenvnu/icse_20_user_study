/** 
 * ??bitmap
 * @param fd        ????
 * @param maxWidth  ????
 * @param maxHeight ????
 * @return bitmap
 */
public static Bitmap getBitmap(FileDescriptor fd,int maxWidth,int maxHeight){
  if (fd == null) {
    return null;
  }
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inJustDecodeBounds=true;
  BitmapFactory.decodeFileDescriptor(fd,null,options);
  options.inSampleSize=calculateInSampleSize(options,maxWidth,maxHeight);
  options.inJustDecodeBounds=false;
  return BitmapFactory.decodeFileDescriptor(fd,null,options);
}
