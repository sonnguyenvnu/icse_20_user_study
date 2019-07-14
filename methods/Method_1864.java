@VisibleForTesting public static float determineResizeRatio(ResizeOptions resizeOptions,int width,int height){
  if (resizeOptions == null) {
    return 1.0f;
  }
  final float widthRatio=((float)resizeOptions.width) / width;
  final float heightRatio=((float)resizeOptions.height) / height;
  float ratio=Math.max(widthRatio,heightRatio);
  if (width * ratio > resizeOptions.maxBitmapSize) {
    ratio=resizeOptions.maxBitmapSize / width;
  }
  if (height * ratio > resizeOptions.maxBitmapSize) {
    ratio=resizeOptions.maxBitmapSize / height;
  }
  return ratio;
}
