/** 
 * swallow-result -> background-thread hand-off -> multiplex -> encoded cache -> disk cache -> (webp transcode) -> local file fetch.
 */
private synchronized Producer<Void> getLocalFileFetchToEncodedMemoryPrefetchSequence(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalFileFetchToEncodedMemoryPrefetchSequence");
  }
  if (mLocalFileFetchToEncodedMemoryPrefetchSequence == null) {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalFileFetchToEncodedMemoryPrefetchSequence:init");
    }
    mLocalFileFetchToEncodedMemoryPrefetchSequence=ProducerFactory.newSwallowResultProducer(getBackgroundLocalFileFetchToEncodeMemorySequence());
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return mLocalFileFetchToEncodedMemoryPrefetchSequence;
}
