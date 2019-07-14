private void maybeStartInputProducer(Consumer<EncodedImage> consumerOfDiskCacheWriteProducer,ProducerContext producerContext){
  if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.DISK_CACHE.getValue()) {
    consumerOfDiskCacheWriteProducer.onNewResult(null,Consumer.IS_LAST);
  }
 else {
    Consumer<EncodedImage> consumer;
    if (producerContext.getImageRequest().isDiskCacheEnabled()) {
      consumer=new DiskCacheWriteConsumer(consumerOfDiskCacheWriteProducer,producerContext,mDefaultBufferedDiskCache,mSmallImageBufferedDiskCache,mCacheKeyFactory);
    }
 else {
      consumer=consumerOfDiskCacheWriteProducer;
    }
    mInputProducer.produceResults(consumer,producerContext);
  }
}
