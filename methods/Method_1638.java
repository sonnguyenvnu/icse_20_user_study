protected Pair<CacheKey,ImageRequest.RequestLevel> getKey(ProducerContext producerContext){
  return Pair.create(mCacheKeyFactory.getEncodedCacheKey(producerContext.getImageRequest(),producerContext.getCallerContext()),producerContext.getLowestPermittedRequestLevel());
}
