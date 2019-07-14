@Override public boolean canTranscode(ImageFormat imageFormat){
  return imageFormat == DefaultImageFormats.HEIF || imageFormat == DefaultImageFormats.JPEG;
}
