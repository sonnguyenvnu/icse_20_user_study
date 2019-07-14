private static int extractOrientationFromMetadata(EncodedImage encodedImage){
switch (encodedImage.getRotationAngle()) {
case RotationOptions.ROTATE_90:
case RotationOptions.ROTATE_180:
case RotationOptions.ROTATE_270:
    return encodedImage.getRotationAngle();
default :
  return 0;
}
}
