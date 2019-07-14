/** 
 * Gets the explicit angle to rotate to, if one was set.
 * @throws IllegalStateException if the instance was create using one of the{@code autoRotate()} constructors.
 */
public @RotationAngle int getForcedAngle(){
  if (useImageMetadata()) {
    throw new IllegalStateException("Rotation is set to use EXIF");
  }
  return mRotation;
}
