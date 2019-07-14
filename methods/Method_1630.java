protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(final Consumer<CloseableReference<CloseableImage>> consumer,final CacheKey cacheKey,final boolean isMemoryCacheEnabled){
  return new DelegatingConsumer<CloseableReference<CloseableImage>,CloseableReference<CloseableImage>>(consumer){
    @Override public void onNewResultImpl(    CloseableReference<CloseableImage> newResult,    @Status int status){
      try {
        if (FrescoSystrace.isTracing()) {
          FrescoSystrace.beginSection("BitmapMemoryCacheProducer#onNewResultImpl");
        }
        final boolean isLast=isLast(status);
        if (newResult == null) {
          if (isLast) {
            getConsumer().onNewResult(null,status);
          }
          return;
        }
        if (newResult.get().isStateful() || statusHasFlag(status,IS_PARTIAL_RESULT)) {
          getConsumer().onNewResult(newResult,status);
          return;
        }
        if (!isLast) {
          CloseableReference<CloseableImage> currentCachedResult=mMemoryCache.get(cacheKey);
          if (currentCachedResult != null) {
            try {
              QualityInfo newInfo=newResult.get().getQualityInfo();
              QualityInfo cachedInfo=currentCachedResult.get().getQualityInfo();
              if (cachedInfo.isOfFullQuality() || cachedInfo.getQuality() >= newInfo.getQuality()) {
                getConsumer().onNewResult(currentCachedResult,status);
                return;
              }
            }
  finally {
              CloseableReference.closeSafely(currentCachedResult);
            }
          }
        }
        CloseableReference<CloseableImage> newCachedResult=null;
        if (isMemoryCacheEnabled) {
          newCachedResult=mMemoryCache.cache(cacheKey,newResult);
        }
        try {
          if (isLast) {
            getConsumer().onProgressUpdate(1f);
          }
          getConsumer().onNewResult((newCachedResult != null) ? newCachedResult : newResult,status);
        }
  finally {
          CloseableReference.closeSafely(newCachedResult);
        }
      }
  finally {
        if (FrescoSystrace.isTracing()) {
          FrescoSystrace.endSection();
        }
      }
    }
  }
;
}
