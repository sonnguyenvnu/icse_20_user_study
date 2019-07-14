private boolean shouldUseLargeImageView(ImageInfo imageInfo){
  if (TextUtils.equals(imageInfo.mimeType,"image/gif")) {
    return false;
  }
  if (imageInfo.width <= 0 || imageInfo.height <= 0) {
    return false;
  }
  if (imageInfo.width > 2048 || imageInfo.height > 2048) {
    float ratio=(float)imageInfo.width / imageInfo.height;
    if (ratio < 0.5 || ratio > 2) {
      return true;
    }
  }
  return false;
}
