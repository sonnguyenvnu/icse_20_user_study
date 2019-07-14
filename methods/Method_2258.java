@Override @Nullable public Drawable createDrawable(CloseableImage closeableImage,ImageOptions imageOptions){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("BitmapDrawableFactory#createDrawable");
    }
    if (closeableImage instanceof CloseableStaticBitmap) {
      return handleCloseableStaticBitmap((CloseableStaticBitmap)closeableImage,imageOptions);
    }
    return null;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
