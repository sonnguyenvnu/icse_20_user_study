public EncodedMemoryCacheProducer newEncodedMemoryCacheProducer(Producer<EncodedImage> inputProducer){
  return new EncodedMemoryCacheProducer(mEncodedMemoryCache,mCacheKeyFactory,inputProducer);
}
