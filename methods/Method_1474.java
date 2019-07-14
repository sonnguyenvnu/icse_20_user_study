/** 
 * background-thread hand-off -> multiplex -> encoded cache -> disk cache -> (webp transcode) -> local file fetch
 */
private synchronized Producer<EncodedImage> getBackgroundLocalFileFetchToEncodeMemorySequence(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalFileFetchToEncodeMemorySequence");
  }
  if (mBackgroundLocalFileFetchToEncodedMemorySequence == null) {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalFileFetchToEncodeMemorySequence:init");
    }
    final LocalFileFetchProducer localFileFetchProducer=mProducerFactory.newLocalFileFetchProducer();
    final Producer<EncodedImage> toEncodedMultiplexProducer=newEncodedCacheMultiplexToTranscodeSequence(localFileFetchProducer);
    mBackgroundLocalFileFetchToEncodedMemorySequence=mProducerFactory.newBackgroundThreadHandoffProducer(toEncodedMultiplexProducer,mThreadHandoffProducerQueue);
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return mBackgroundLocalFileFetchToEncodedMemorySequence;
}
