@VisibleForTesting public static BitmapFactory.Options getBitmapFactoryOptions(int sampleSize,Bitmap.Config bitmapConfig){
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inDither=true;
  options.inPreferredConfig=bitmapConfig;
  options.inPurgeable=true;
  options.inInputShareable=true;
  options.inSampleSize=sampleSize;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    options.inMutable=true;
  }
  return options;
}
