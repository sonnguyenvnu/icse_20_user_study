@VisibleForTesting void displayErrorImage(FrescoState frescoState,@Nullable Drawable errorDrawable){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#displayErrorImage");
  }
  try {
    if (!frescoState.isAttached()) {
      return;
    }
    frescoState.getFrescoDrawable().setProgressDrawable(null);
    if (errorDrawable != null) {
      frescoState.getFrescoDrawable().setImageDrawable(errorDrawable);
    }
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
