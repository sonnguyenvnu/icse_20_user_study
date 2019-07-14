public BitmapMemoryCacheProducer newBitmapMemoryCacheProducer(Producer<CloseableReference<CloseableImage>> inputProducer){
  return new BitmapMemoryCacheProducer(mBitmapMemoryCache,mCacheKeyFactory,inputProducer);
}
