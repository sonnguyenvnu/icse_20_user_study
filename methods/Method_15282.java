/** 
 * ????
 * @param cornerRadiusSize
 * @param defaultImageResId
 * @return
 */
@SuppressWarnings("deprecation") private static DisplayImageOptions getOption(int cornerRadiusSize,int defaultImageResId){
  Options options0=new Options();
  options0.inPreferredConfig=Bitmap.Config.RGB_565;
  DisplayImageOptions.Builder builder=new DisplayImageOptions.Builder();
  if (defaultImageResId > 0) {
    try {
      builder.showImageForEmptyUri(defaultImageResId).showImageOnLoading(defaultImageResId).showImageOnFail(defaultImageResId);
    }
 catch (    Exception e) {
      Log.e(TAG,"getOption  try {builder.showImageForEmptyUri(defaultImageResId) ..." + " >> } catch (Exception e) { \n" + e.getMessage());
    }
  }
  if (cornerRadiusSize > 0) {
    builder.displayer(new RoundedBitmapDisplayer(cornerRadiusSize));
  }
  return builder.cacheInMemory(true).cacheOnDisc(true).decodingOptions(options0).build();
}
