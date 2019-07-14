@Override public int getBitmapSize(final int width,final int height,final BitmapFactory.Options options){
  return hasColorGamutMismatch(options) ? width * height * 8 : BitmapUtil.getSizeInByteForBitmap(width,height,options.inPreferredConfig);
}
