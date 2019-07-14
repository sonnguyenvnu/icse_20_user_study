public static int getSoftwareNumerator(RotationOptions rotationOptions,@Nullable ResizeOptions resizeOptions,EncodedImage encodedImage,boolean resizingEnabled){
  if (!resizingEnabled) {
    return SCALE_DENOMINATOR;
  }
  if (resizeOptions == null) {
    return SCALE_DENOMINATOR;
  }
  final int rotationAngle=getRotationAngle(rotationOptions,encodedImage);
  int exifOrientation=ExifInterface.ORIENTATION_UNDEFINED;
  if (INVERTED_EXIF_ORIENTATIONS.contains(encodedImage.getExifOrientation())) {
    exifOrientation=getForceRotatedInvertedExifOrientation(rotationOptions,encodedImage);
  }
  final boolean swapDimensions=rotationAngle == 90 || rotationAngle == 270 || exifOrientation == ExifInterface.ORIENTATION_TRANSPOSE || exifOrientation == ExifInterface.ORIENTATION_TRANSVERSE;
  final int widthAfterRotation=swapDimensions ? encodedImage.getHeight() : encodedImage.getWidth();
  final int heightAfterRotation=swapDimensions ? encodedImage.getWidth() : encodedImage.getHeight();
  float ratio=determineResizeRatio(resizeOptions,widthAfterRotation,heightAfterRotation);
  int numerator=roundNumerator(ratio,resizeOptions.roundUpFraction);
  if (numerator > SCALE_DENOMINATOR) {
    return SCALE_DENOMINATOR;
  }
  return (numerator < 1) ? 1 : numerator;
}
