private static int getThumbnailKind(ResizeOptions resizeOptions){
  if (ThumbnailSizeChecker.isImageBigEnough(MICRO_THUMBNAIL_DIMENSIONS.width(),MICRO_THUMBNAIL_DIMENSIONS.height(),resizeOptions)) {
    return MediaStore.Images.Thumbnails.MICRO_KIND;
  }
 else   if (ThumbnailSizeChecker.isImageBigEnough(MINI_THUMBNAIL_DIMENSIONS.width(),MINI_THUMBNAIL_DIMENSIONS.height(),resizeOptions)) {
    return MediaStore.Images.Thumbnails.MINI_KIND;
  }
 else {
    return NO_THUMBNAIL;
  }
}
