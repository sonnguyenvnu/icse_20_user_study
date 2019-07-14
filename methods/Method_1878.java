/** 
 * Determines auto-rotate angle based on orientation information.
 * @param orientation orientation information read from APP1 EXIF (TIFF) block.
 * @return orientation: 1/3/6/8 -> 0/180/90/270. Returns 0 for inverted orientations (2/4/5/7).
 */
public static int getAutoRotateAngleFromOrientation(int orientation){
switch (orientation) {
case ExifInterface.ORIENTATION_NORMAL:
case ExifInterface.ORIENTATION_UNDEFINED:
    return 0;
case ExifInterface.ORIENTATION_ROTATE_180:
  return 180;
case ExifInterface.ORIENTATION_ROTATE_90:
return 90;
case ExifInterface.ORIENTATION_ROTATE_270:
return 270;
}
return 0;
}
