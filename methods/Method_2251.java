@Nullable @Override public Drawable setupActualImageDrawable(FrescoContext frescoContext,FrescoDrawable frescoDrawable,Resources resources,ImageOptions imageOptions,CloseableReference<CloseableImage> closeableImage,@Nullable ForwardingDrawable actualImageWrapperDrawable,boolean wasImmediate){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("HierarcherImpl#setupActualImageDrawable");
  }
  try {
    Drawable actualDrawable=mDrawableFactory.createDrawable(closeableImage.get(),imageOptions);
    if (actualImageWrapperDrawable == null) {
      actualImageWrapperDrawable=buildActualImageWrapper(imageOptions);
    }
    actualImageWrapperDrawable.setCurrent(actualDrawable != null ? actualDrawable : NOP_DRAWABLE);
    frescoDrawable.setImage(actualImageWrapperDrawable,closeableImage);
    if (!frescoDrawable.isDefaultLayerIsOn()) {
      if (wasImmediate || imageOptions.getFadeDurationMs() <= 0) {
        frescoDrawable.showImageImmediately();
      }
 else {
        frescoDrawable.fadeInImage(imageOptions.getFadeDurationMs());
      }
    }
 else {
      frescoDrawable.setPlaceholderDrawable(null);
    }
    return actualDrawable;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
