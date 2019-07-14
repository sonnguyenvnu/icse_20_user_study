@Override public void onAttach(FrescoState frescoState,@Nullable ImageListener imageListener){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#onAttach");
  }
  try {
    final FrescoExperiments experiments=mFrescoContext.getExperiments();
    if (experiments.closeDatasource()) {
      DeferredReleaser.getInstance().cancelDeferredRelease(frescoState);
    }
    frescoState.setAttached(true);
    frescoState.setImageListener(imageListener);
    final ImageRequest imageRequest=frescoState.getImageRequest();
    if (frescoState.getImageOptions().shouldResizeToViewport() && frescoState.getTargetWidthPx() > 0 && frescoState.getTargetHeightPx() > 0 && imageRequest != null && imageRequest.getResizeOptions() == null) {
      frescoState.setImageRequest(ImageRequestBuilder.fromRequest(imageRequest).setResizeOptions(ResizeOptions.forDimensions(frescoState.getTargetWidthPx(),frescoState.getTargetHeightPx())).setResizingAllowedOverride(true).build());
    }
    if (!frescoState.getFrescoDrawable().isDefaultLayerIsOn()) {
      frescoState.getFrescoDrawable().reset();
    }
    mFrescoContext.getHierarcher().setupOverlayDrawable(mFrescoContext,frescoState.getFrescoDrawable(),frescoState.getResources(),frescoState.getImageOptions(),frescoState.getOverlayDrawable(),mDebugOverlayFactory.create(frescoState));
    frescoState.onSubmit(frescoState.getId(),frescoState.getCallerContext());
    CloseableReference<CloseableImage> cachedImage=frescoState.getCachedImage();
    try {
      if (CloseableReference.isValid(cachedImage)) {
        frescoState.setImageOrigin(ImageOrigin.MEMORY_BITMAP);
        displayResultOrError(frescoState,cachedImage,true);
        return;
      }
    }
  finally {
      CloseableReference.closeSafely(cachedImage);
    }
    if (experiments.checkCacheInAttach()) {
      if (FrescoSystrace.isTracing()) {
        FrescoSystrace.beginSection("FrescoControllerImpl#onAttach->getCachedImage");
      }
      try {
        try {
          cachedImage=getCachedImage(frescoState.getCacheKey());
        }
  finally {
          if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
          }
        }
        if (CloseableReference.isValid(cachedImage)) {
          if (experiments.cacheImageInState()) {
            frescoState.setCachedImage(cachedImage);
          }
          frescoState.setImageOrigin(ImageOrigin.MEMORY_BITMAP);
          displayResultOrError(frescoState,cachedImage,true);
          return;
        }
      }
  finally {
        CloseableReference.closeSafely(cachedImage);
      }
    }
    final Drawable placeholderDrawable;
    if (frescoState.getPlaceholderDrawable() != null) {
      placeholderDrawable=frescoState.getPlaceholderDrawable();
    }
 else {
      placeholderDrawable=mFrescoContext.getHierarcher().buildPlaceholderDrawable(frescoState.getResources(),frescoState.getImageOptions());
    }
    frescoState.getFrescoDrawable().setImageDrawable(placeholderDrawable);
    final Drawable progressDrawable=mFrescoContext.getHierarcher().buildProgressDrawable(frescoState.getResources(),frescoState.getImageOptions());
    frescoState.getFrescoDrawable().setProgressDrawable(progressDrawable);
    if (frescoState.getImageRequest() != null || frescoState.getMultiUri() != null) {
      if (FrescoSystrace.isTracing()) {
        FrescoSystrace.beginSection("FrescoControllerImpl#onAttach->fetch");
      }
      try {
        DataSource<CloseableReference<CloseableImage>> dataSource;
        if (frescoState.getUri() != null) {
          dataSource=fireOffRequest(frescoState);
        }
 else {
          dataSource=MultiUri.getMultiUriDatasourceSupplier(mFrescoContext.getImagePipeline(),frescoState).get();
        }
        dataSource.subscribe(frescoState,mFrescoContext.getUiThreadExecutorService());
        if (experiments.keepRefToMainFetchDatasouce()) {
          frescoState.setMainFetchDatasource(dataSource);
        }
      }
  finally {
        if (FrescoSystrace.isTracing()) {
          FrescoSystrace.endSection();
        }
      }
    }
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
