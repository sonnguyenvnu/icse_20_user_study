public static int getRotationAngle(RotationOptions rotationOptions,EncodedImage encodedImage){
  if (!rotationOptions.rotationEnabled()) {
    return RotationOptions.NO_ROTATION;
  }
  int rotationFromMetadata=extractOrientationFromMetadata(encodedImage);
  if (rotationOptions.useImageMetadata()) {
    return rotationFromMetadata;
  }
  return (rotationFromMetadata + rotationOptions.getForcedAngle()) % FULL_ROUND;
}
