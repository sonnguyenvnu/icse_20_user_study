@DoNotStrip private static boolean setOutDimensions(BitmapFactory.Options options,int imageWidth,int imageHeight){
  if (options != null && options.inJustDecodeBounds) {
    options.outWidth=imageWidth;
    options.outHeight=imageHeight;
    return true;
  }
  return false;
}
