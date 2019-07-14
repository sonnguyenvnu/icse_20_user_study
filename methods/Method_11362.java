/** 
 * Determines the rotation to be applied to tiles, based on EXIF orientation or chosen setting.
 */
@AnyThread private int getRequiredRotation(){
  if (orientation == ORIENTATION_USE_EXIF) {
    return sOrientation;
  }
 else {
    return orientation;
  }
}
