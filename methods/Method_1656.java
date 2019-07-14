private static int getRotationAngle(String pathname){
  if (pathname != null) {
    try {
      ExifInterface exif=new ExifInterface(pathname);
      return JfifUtil.getAutoRotateAngleFromOrientation(exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL));
    }
 catch (    IOException ioe) {
      FLog.e(TAG,ioe,"Unable to retrieve thumbnail rotation for %s",pathname);
    }
  }
  return 0;
}
