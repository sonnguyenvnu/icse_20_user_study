@Override protected Drawable createDrawable(CloseableReference<CloseableImage> image){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("PipelineDraweeController#createDrawable");
    }
    Preconditions.checkState(CloseableReference.isValid(image));
    CloseableImage closeableImage=image.get();
    maybeUpdateDebugOverlay(closeableImage);
    Drawable drawable=maybeCreateDrawableFromFactories(mCustomDrawableFactories,closeableImage);
    if (drawable != null) {
      return drawable;
    }
    drawable=maybeCreateDrawableFromFactories(mGlobalDrawableFactories,closeableImage);
    if (drawable != null) {
      return drawable;
    }
    drawable=mDefaultDrawableFactory.createDrawable(closeableImage);
    if (drawable != null) {
      return drawable;
    }
    throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
