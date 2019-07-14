@Override public Drawable buildPlaceholderDrawable(Resources resources,ImageOptions imageOptions){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("HierarcherImpl#buildPlaceholderDrawable");
  }
  try {
    @Nullable Drawable placeholderDrawable=imageOptions.getPlaceholderDrawable();
    if (placeholderDrawable == null && imageOptions.getPlaceholderRes() != 0) {
      placeholderDrawable=resources.getDrawable(imageOptions.getPlaceholderRes());
    }
    if (placeholderDrawable == null) {
      return NOP_DRAWABLE;
    }
    if (imageOptions.getPlaceholderScaleType() != null) {
      return new ScaleTypeDrawable(placeholderDrawable,imageOptions.getPlaceholderScaleType());
    }
    return placeholderDrawable;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
