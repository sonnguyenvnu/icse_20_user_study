private static boolean hasTransformableRotationAngle(CloseableStaticBitmap closeableStaticBitmap){
  return closeableStaticBitmap.getRotationAngle() != 0 && closeableStaticBitmap.getRotationAngle() != EncodedImage.UNKNOWN_ROTATION_ANGLE;
}
