@Override protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(final Consumer<CloseableReference<CloseableImage>> consumer,final CacheKey cacheKey,boolean isMemoryCacheEnabled){
  return consumer;
}
