private void maybeStartInputProducer(Consumer<EncodedImage> consumer,ProducerContext producerContext){
  if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.DISK_CACHE.getValue()) {
    consumer.onNewResult(null,Consumer.IS_LAST);
    return;
  }
  mInputProducer.produceResults(consumer,producerContext);
}
