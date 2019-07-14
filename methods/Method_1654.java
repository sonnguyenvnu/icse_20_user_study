@Override public boolean canProvideImageForSize(ResizeOptions resizeOptions){
  return ThumbnailSizeChecker.isImageBigEnough(MINI_THUMBNAIL_DIMENSIONS.width(),MINI_THUMBNAIL_DIMENSIONS.height(),resizeOptions);
}
