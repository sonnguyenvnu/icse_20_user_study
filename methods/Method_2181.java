private static void setDensityFromOptions(Bitmap outputBitmap,BitmapFactory.Options opts){
  if (outputBitmap == null || opts == null) {
    return;
  }
  final int density=opts.inDensity;
  if (density != 0) {
    outputBitmap.setDensity(density);
    final int targetDensity=opts.inTargetDensity;
    if (targetDensity == 0 || density == targetDensity || density == opts.inScreenDensity) {
      return;
    }
    if (opts.inScaled) {
      outputBitmap.setDensity(targetDensity);
    }
  }
 else   if (IN_BITMAP_SUPPORTED && opts.inBitmap != null) {
    outputBitmap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
  }
}
