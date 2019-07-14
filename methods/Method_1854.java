@VisibleForTesting public static float determineDownsampleRatio(final RotationOptions rotationOptions,@Nullable final ResizeOptions resizeOptions,final EncodedImage encodedImage){
  Preconditions.checkArgument(EncodedImage.isMetaDataAvailable(encodedImage));
  if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
    return 1.0f;
  }
  final int rotationAngle=getRotationAngle(rotationOptions,encodedImage);
  final boolean swapDimensions=rotationAngle == 90 || rotationAngle == 270;
  final int widthAfterRotation=swapDimensions ? encodedImage.getHeight() : encodedImage.getWidth();
  final int heightAfterRotation=swapDimensions ? encodedImage.getWidth() : encodedImage.getHeight();
  final float widthRatio=((float)resizeOptions.width) / widthAfterRotation;
  final float heightRatio=((float)resizeOptions.height) / heightAfterRotation;
  float ratio=Math.max(widthRatio,heightRatio);
  FLog.v("DownsampleUtil","Downsample - Specified size: %dx%d, image size: %dx%d " + "ratio: %.1f x %.1f, ratio: %.3f",resizeOptions.width,resizeOptions.height,widthAfterRotation,heightAfterRotation,widthRatio,heightRatio,ratio);
  return ratio;
}
