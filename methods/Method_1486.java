private Producer<EncodedImage> newDiskCacheSequence(Producer<EncodedImage> inputProducer){
  Producer<EncodedImage> cacheWriteProducer;
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ProducerSequenceFactory#newDiskCacheSequence");
  }
  if (mPartialImageCachingEnabled) {
    Producer<EncodedImage> partialDiskCacheProducer=mProducerFactory.newPartialDiskCacheProducer(inputProducer);
    cacheWriteProducer=mProducerFactory.newDiskCacheWriteProducer(partialDiskCacheProducer);
  }
 else {
    cacheWriteProducer=mProducerFactory.newDiskCacheWriteProducer(inputProducer);
  }
  DiskCacheReadProducer result=mProducerFactory.newDiskCacheReadProducer(cacheWriteProducer);
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return result;
}
