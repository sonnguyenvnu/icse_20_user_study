/** 
 * Returns suitable Bitmap Config for the new Bitmap based on the source Bitmap configurations.
 * @param source the source Bitmap
 * @return the Bitmap Config for the new Bitmap
 */
private static Bitmap.Config getSuitableBitmapConfig(Bitmap source){
  Bitmap.Config finalConfig=Bitmap.Config.ARGB_8888;
  final Bitmap.Config sourceConfig=source.getConfig();
  if (sourceConfig != null) {
switch (sourceConfig) {
case RGB_565:
      finalConfig=Bitmap.Config.RGB_565;
    break;
case ALPHA_8:
  finalConfig=Bitmap.Config.ALPHA_8;
break;
case ARGB_4444:
case ARGB_8888:
default :
finalConfig=Bitmap.Config.ARGB_8888;
break;
}
}
return finalConfig;
}
