/** 
 * swallow-result -> background-thread hand-off -> multiplex -> encoded cache -> disk cache -> (webp transcode) -> network fetch.
 */
private synchronized Producer<Void> getNetworkFetchToEncodedMemoryPrefetchSequence(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchToEncodedMemoryPrefetchSequence");
  }
  if (mNetworkFetchToEncodedMemoryPrefetchSequence == null) {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchToEncodedMemoryPrefetchSequence:init");
    }
    mNetworkFetchToEncodedMemoryPrefetchSequence=ProducerFactory.newSwallowResultProducer(getBackgroundNetworkFetchToEncodedMemorySequence());
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return mNetworkFetchToEncodedMemoryPrefetchSequence;
}
