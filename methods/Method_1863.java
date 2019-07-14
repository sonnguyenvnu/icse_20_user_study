/** 
 * Get an inverted exif orientation (2, 4, 5, 7) but adjusted to take the force rotation angle into consideration
 * @throws IllegalArgumentException if encoded image passed doesn't have an inverted EXIForientation
 */
public static int getForceRotatedInvertedExifOrientation(RotationOptions rotationOptions,EncodedImage encodedImage){
  int exifOrientation=encodedImage.getExifOrientation();
  int index=INVERTED_EXIF_ORIENTATIONS.indexOf(exifOrientation);
  if (index < 0) {
    throw new IllegalArgumentException("Only accepts inverted exif orientations");
  }
  int forcedAngle=RotationOptions.NO_ROTATION;
  if (!rotationOptions.useImageMetadata()) {
    forcedAngle=rotationOptions.getForcedAngle();
  }
  int timesToRotate=forcedAngle / 90;
  return INVERTED_EXIF_ORIENTATIONS.get((index + timesToRotate) % INVERTED_EXIF_ORIENTATIONS.size());
}
