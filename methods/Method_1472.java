/** 
 * multiplex -> encoded cache -> disk cache -> (webp transcode) -> network fetch. 
 */
private synchronized Producer<EncodedImage> getCommonNetworkFetchToEncodedMemorySequence(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ProducerSequenceFactory#getCommonNetworkFetchToEncodedMemorySequence");
  }
  if (mCommonNetworkFetchToEncodedMemorySequence == null) {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("ProducerSequenceFactory#getCommonNetworkFetchToEncodedMemorySequence:init");
    }
    Producer<EncodedImage> inputProducer=newEncodedCacheMultiplexToTranscodeSequence(mProducerFactory.newNetworkFetchProducer(mNetworkFetcher));
    mCommonNetworkFetchToEncodedMemorySequence=ProducerFactory.newAddImageTransformMetaDataProducer(inputProducer);
    mCommonNetworkFetchToEncodedMemorySequence=mProducerFactory.newResizeAndRotateProducer(mCommonNetworkFetchToEncodedMemorySequence,mResizeAndRotateEnabledForNetwork && !mDownsampleEnabled,mImageTranscoderFactory);
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return mCommonNetworkFetchToEncodedMemorySequence;
}
