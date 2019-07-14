@VisibleForTesting void displayResultOrError(FrescoState frescoState,@Nullable CloseableReference<CloseableImage> result,boolean wasImmediate){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#displayResultOrError");
  }
  try {
    if (!frescoState.isAttached()) {
      return;
    }
    CloseableImage closeableImage;
    if (result == null || (closeableImage=result.get()) == null) {
      Drawable errorDrawable=mFrescoContext.getHierarcher().buildErrorDrawable(frescoState.getResources(),frescoState.getImageOptions());
      displayErrorImage(frescoState,errorDrawable);
      frescoState.onFailure(frescoState.getId(),errorDrawable,null);
      return;
    }
    frescoState.getFrescoDrawable().setProgressDrawable(null);
    Drawable actualDrawable=mFrescoContext.getHierarcher().setupActualImageDrawable(mFrescoContext,frescoState.getFrescoDrawable(),frescoState.getResources(),frescoState.getImageOptions(),result,frescoState.getActualImageWrapper(),wasImmediate);
    frescoState.onFinalImageSet(frescoState.getId(),frescoState.getImageOrigin(),closeableImage,actualDrawable);
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
