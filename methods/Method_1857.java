private static int getRotationAngle(final RotationOptions rotationOptions,final EncodedImage encodedImage){
  if (!rotationOptions.useImageMetadata()) {
    return 0;
  }
  int rotationAngle=encodedImage.getRotationAngle();
  Preconditions.checkArgument(rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270);
  return rotationAngle;
}
