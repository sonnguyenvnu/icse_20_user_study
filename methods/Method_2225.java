@Override public FrescoState createState(@Nullable Uri uri,@Nullable MultiUri multiUri,ImageOptions imageOptions,@Nullable Object callerContext,Resources resources,@Nullable ImageListener imageListener){
  mFrescoContext.verifyCallerContext(callerContext);
  validateUris(uri,multiUri);
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#createState");
  }
  try {
    final FrescoExperiments frescoExperiments=mFrescoContext.getExperiments();
    final ImageRequest imageRequest=mFrescoContext.buildImageRequest(uri,imageOptions);
    final CacheKey cacheKey=mFrescoContext.getImagePipeline().getCacheKey(imageRequest,callerContext);
    CloseableReference<CloseableImage> cachedImage=null;
    boolean isImageCached;
    if (frescoExperiments.cacheImageInState()) {
      cachedImage=getCachedImage(cacheKey);
      isImageCached=cachedImage != null;
    }
 else {
      isImageCached=mFrescoContext.getImagePipeline().hasCachedImage(cacheKey);
    }
    FrescoState frescoState=new FrescoState(FrescoContext.generateIdentifier(),mFrescoContext,uri,multiUri,imageOptions,callerContext,imageRequest,cacheKey,cachedImage,resources,imageListener,new ForwardingImageListener(mFrescoContext.getGlobalImageListener(),imageOptions.shouldAutoPlay() ? AutoPlayImageListener.getInstance() : null));
    if (frescoExperiments.prepareActualImageWrapperInBackground()) {
      prepareActualImageInBackground(frescoState);
    }
    if (!isImageCached) {
      if (frescoExperiments.prepareImagePipelineComponents() && imageRequest != null) {
        prepareImagePipelineComponents(frescoState,imageRequest,callerContext);
      }
      if (frescoExperiments.prefetchInOnPrepare()) {
        DataSource<CloseableReference<CloseableImage>> datasource=fireOffRequest(frescoState);
        datasource.subscribe(frescoState,mFrescoContext.getUiThreadExecutorService());
        if (frescoExperiments.keepRefToPrefetchDatasouce()) {
          frescoState.setPrefetchDatasource(datasource);
        }
      }
      if (frescoExperiments.preparePlaceholderDrawableInBackground()) {
        final Drawable placeholderDrawable=mFrescoContext.getHierarcher().buildPlaceholderDrawable(frescoState.getResources(),frescoState.getImageOptions());
        frescoState.setPlaceholderDrawable(placeholderDrawable);
      }
    }
    return frescoState;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
