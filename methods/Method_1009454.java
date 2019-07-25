public static Bitmap resize(String imagePath,int maxWidth){
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inJustDecodeBounds=true;
  BitmapFactory.decodeFile(imagePath,options);
  int srcWidth=options.outWidth;
  int srcHeight=options.outHeight;
  if (srcWidth <= 0 || srcHeight <= 0)   return null;
  if (maxWidth > srcWidth)   maxWidth=srcWidth;
  int inSampleSize=1;
  while (srcWidth / 2 > maxWidth) {
    srcWidth/=2;
    srcHeight/=2;
    inSampleSize*=2;
  }
  float desiredScale=(float)maxWidth / srcWidth;
  options.inJustDecodeBounds=false;
  options.inDither=false;
  options.inSampleSize=inSampleSize;
  options.inScaled=false;
  options.inPreferredConfig=Bitmap.Config.ARGB_8888;
  Bitmap sampledSrcBitmap=BitmapFactory.decodeFile(imagePath,options);
  Matrix matrix=getRotationMatrix(imagePath);
  matrix.postScale(desiredScale,desiredScale);
  Bitmap result=Bitmap.createBitmap(sampledSrcBitmap,0,0,sampledSrcBitmap.getWidth(),sampledSrcBitmap.getHeight(),matrix,true);
  if (result != sampledSrcBitmap) {
    sampledSrcBitmap.recycle();
  }
  return result;
}
