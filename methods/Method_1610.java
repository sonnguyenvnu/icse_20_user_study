/** 
 * Options returned by this method are configured with mDecodeBuffer which is GuardedBy("this")
 */
private static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage encodedImage,Bitmap.Config bitmapConfig){
  final BitmapFactory.Options options=new BitmapFactory.Options();
  options.inSampleSize=encodedImage.getSampleSize();
  options.inJustDecodeBounds=true;
  BitmapFactory.decodeStream(encodedImage.getInputStream(),null,options);
  if (options.outWidth == -1 || options.outHeight == -1) {
    throw new IllegalArgumentException();
  }
  options.inJustDecodeBounds=false;
  options.inDither=true;
  options.inPreferredConfig=bitmapConfig;
  options.inMutable=true;
  return options;
}
