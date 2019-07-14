private void checkBuffer(final Bitmap buffer){
  if (buffer.isRecycled()) {
    throw new IllegalArgumentException("Bitmap is recycled");
  }
  if (buffer.getWidth() < mGifInfoHandle.getWidth() || buffer.getHeight() < mGifInfoHandle.getHeight()) {
    throw new IllegalArgumentException("Bitmap ia too small, size must be greater than or equal to GIF size");
  }
  if (buffer.getConfig() != Bitmap.Config.ARGB_8888) {
    throw new IllegalArgumentException("Only Config.ARGB_8888 is supported. Current bitmap config: " + buffer.getConfig());
  }
}
