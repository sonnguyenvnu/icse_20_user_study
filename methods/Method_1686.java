public static boolean isImageBigEnough(EncodedImage encodedImage,ResizeOptions resizeOptions){
  if (encodedImage == null) {
    return false;
  }
switch (encodedImage.getRotationAngle()) {
case ROTATED_90_DEGREES_CLOCKWISE:
case ROTATED_90_DEGREES_COUNTER_CLOCKWISE:
    return isImageBigEnough(encodedImage.getHeight(),encodedImage.getWidth(),resizeOptions);
default :
  return isImageBigEnough(encodedImage.getWidth(),encodedImage.getHeight(),resizeOptions);
}
}
