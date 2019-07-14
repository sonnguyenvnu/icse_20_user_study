@Override protected @Nullable CloseableReference<CloseableImage> getCachedImage(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("PipelineDraweeController#getCachedImage");
  }
  try {
    if (mMemoryCache == null || mCacheKey == null) {
      return null;
    }
    CloseableReference<CloseableImage> closeableImage=mMemoryCache.get(mCacheKey);
    if (closeableImage != null && !closeableImage.get().getQualityInfo().isOfFullQuality()) {
      closeableImage.close();
      return null;
    }
    return closeableImage;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
