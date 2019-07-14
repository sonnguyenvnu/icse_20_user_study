@Override public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer,final ProducerContext producerContext){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("BitmapMemoryCacheProducer#produceResults");
    }
    final ProducerListener listener=producerContext.getListener();
    final String requestId=producerContext.getId();
    listener.onProducerStart(requestId,getProducerName());
    final ImageRequest imageRequest=producerContext.getImageRequest();
    final Object callerContext=producerContext.getCallerContext();
    final CacheKey cacheKey=mCacheKeyFactory.getBitmapCacheKey(imageRequest,callerContext);
    CloseableReference<CloseableImage> cachedReference=mMemoryCache.get(cacheKey);
    if (cachedReference != null) {
      boolean isFinal=cachedReference.get().getQualityInfo().isOfFullQuality();
      if (isFinal) {
        listener.onProducerFinishWithSuccess(requestId,getProducerName(),listener.requiresExtraMap(requestId) ? ImmutableMap.of(EXTRA_CACHED_VALUE_FOUND,"true") : null);
        listener.onUltimateProducerReached(requestId,getProducerName(),true);
        consumer.onProgressUpdate(1f);
      }
      consumer.onNewResult(cachedReference,BaseConsumer.simpleStatusForIsLast(isFinal));
      cachedReference.close();
      if (isFinal) {
        return;
      }
    }
    if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
      listener.onProducerFinishWithSuccess(requestId,getProducerName(),listener.requiresExtraMap(requestId) ? ImmutableMap.of(EXTRA_CACHED_VALUE_FOUND,"false") : null);
      listener.onUltimateProducerReached(requestId,getProducerName(),false);
      consumer.onNewResult(null,Consumer.IS_LAST);
      return;
    }
    Consumer<CloseableReference<CloseableImage>> wrappedConsumer=wrapConsumer(consumer,cacheKey,producerContext.getImageRequest().isMemoryCacheEnabled());
    listener.onProducerFinishWithSuccess(requestId,getProducerName(),listener.requiresExtraMap(requestId) ? ImmutableMap.of(EXTRA_CACHED_VALUE_FOUND,"false") : null);
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("mInputProducer.produceResult");
    }
    mInputProducer.produceResults(wrappedConsumer,producerContext);
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
