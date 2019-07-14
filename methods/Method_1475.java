/** 
 * background-thread hand-off -> multiplex -> encoded cache -> disk cache -> (webp transcode) -> local content resolver fetch
 */
private synchronized Producer<EncodedImage> getBackgroundLocalContentUriFetchToEncodeMemorySequence(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalContentUriFetchToEncodeMemorySequence");
  }
  if (mBackgroundLocalContentUriFetchToEncodedMemorySequence == null) {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalContentUriFetchToEncodeMemorySequence:init");
    }
    final LocalContentUriFetchProducer localFileFetchProducer=mProducerFactory.newLocalContentUriFetchProducer();
    final Producer<EncodedImage> toEncodedMultiplexProducer=newEncodedCacheMultiplexToTranscodeSequence(localFileFetchProducer);
    mBackgroundLocalContentUriFetchToEncodedMemorySequence=mProducerFactory.newBackgroundThreadHandoffProducer(toEncodedMultiplexProducer,mThreadHandoffProducerQueue);
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return mBackgroundLocalContentUriFetchToEncodedMemorySequence;
}
