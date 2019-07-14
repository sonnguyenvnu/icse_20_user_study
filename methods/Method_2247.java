@Override public Drawable buildProgressDrawable(Resources resources,ImageOptions imageOptions){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("HierarcherImpl#buildProgressDrawable");
  }
  try {
    if (imageOptions.getProgressRes() == 0 && imageOptions.getProgressDrawable() == null) {
      return NOP_DRAWABLE;
    }
    Drawable progressDrawable=imageOptions.getProgressDrawable();
    if (progressDrawable == null) {
      progressDrawable=resources.getDrawable(imageOptions.getProgressRes());
    }
    if (imageOptions.getProgressScaleType() != null) {
      return new ScaleTypeDrawable(progressDrawable,imageOptions.getProgressScaleType());
    }
    return progressDrawable;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
