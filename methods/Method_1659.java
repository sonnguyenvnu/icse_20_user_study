private int getRotationAngle(final ExifInterface exifInterface){
  return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt(exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION)));
}
