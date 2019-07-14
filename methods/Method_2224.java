@Override public FrescoState onPrepare(final FrescoState frescoState,final @Nullable Uri uri,final @Nullable MultiUri multiUri,final ImageOptions imageOptions,final @Nullable Object callerContext,final Resources resources,final @Nullable ImageListener imageListener){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#onPrepare");
  }
  try {
    final boolean stateHasSameProps=frescoState != null && ObjectsCompat.equals(uri,frescoState.getUri()) && ObjectsCompat.equals(multiUri,frescoState.getMultiUri()) && ObjectsCompat.equals(imageOptions,frescoState.getImageOptions()) && (mFrescoContext.getExperiments().shouldDiffCallerContext() ? ObjectsCompat.equals(callerContext,frescoState.getCallerContext()) : true);
    return stateHasSameProps ? frescoState : createState(uri,multiUri,imageOptions,callerContext,resources,imageListener);
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
