private static boolean hasTransformableExifOrientation(CloseableStaticBitmap closeableStaticBitmap){
  return closeableStaticBitmap.getExifOrientation() != ExifInterface.ORIENTATION_NORMAL && closeableStaticBitmap.getExifOrientation() != ExifInterface.ORIENTATION_UNDEFINED;
}
