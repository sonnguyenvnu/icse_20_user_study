/** 
 * background-thread hand-off -> multiplex -> encoded cache -> disk cache -> (webp transcode) -> network fetch.
 */
private synchronized Producer<EncodedImage> getBackgroundNetworkFetchToEncodedMemorySequence(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundNetworkFetchToEncodedMemorySequence");
  }
  if (mBackgroundNetworkFetchToEncodedMemorySequence == null) {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundNetworkFetchToEncodedMemorySequence:init");
    }
    mBackgroundNetworkFetchToEncodedMemorySequence=mProducerFactory.newBackgroundThreadHandoffProducer(getCommonNetworkFetchToEncodedMemorySequence(),mThreadHandoffProducerQueue);
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return mBackgroundNetworkFetchToEncodedMemorySequence;
}
