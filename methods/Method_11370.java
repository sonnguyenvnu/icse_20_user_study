/** 
 * Returns the actual orientation of the image relative to the source file. This will be based on the source file's EXIF orientation if you're using ORIENTATION_USE_EXIF. Values are 0, 90, 180, 270.
 */
public final int getAppliedOrientation(){
  return getRequiredRotation();
}
