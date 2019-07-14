@Override @Nullable public Drawable buildErrorDrawable(Resources resources,ImageOptions imageOptions){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("HierarcherImpl#buildErrorDrawable");
  }
  try {
    if (imageOptions.getErrorRes() == 0) {
      return null;
    }
    Drawable drawable=resources.getDrawable(imageOptions.getErrorRes());
    if (imageOptions.getErrorScaleType() != null) {
      return new ScaleTypeDrawable(drawable,imageOptions.getErrorScaleType(),imageOptions.getErrorFocusPoint());
    }
    return drawable;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
