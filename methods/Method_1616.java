/** 
 * Check if the color space has a wide color gamut and is consistent with the Bitmap config 
 */
private static boolean hasColorGamutMismatch(final BitmapFactory.Options options){
  return options.outColorSpace != null && options.outColorSpace.isWideGamut() && options.inPreferredConfig != Bitmap.Config.RGBA_F16;
}
