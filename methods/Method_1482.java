/** 
 * bitmap cache get -> background thread hand-off -> bitmap cache -> decode -> resize and rotate -> (webp transcode) -> data fetch.
 */
private synchronized Producer<CloseableReference<CloseableImage>> getDataFetchSequence(){
  if (mDataFetchSequence == null) {
    Producer<EncodedImage> inputProducer=mProducerFactory.newDataFetchProducer();
    if (WebpSupportStatus.sIsWebpSupportRequired && (!mWebpSupportEnabled || WebpSupportStatus.sWebpBitmapFactory == null)) {
      inputProducer=mProducerFactory.newWebpTranscodeProducer(inputProducer);
    }
    inputProducer=mProducerFactory.newAddImageTransformMetaDataProducer(inputProducer);
    inputProducer=mProducerFactory.newResizeAndRotateProducer(inputProducer,true,mImageTranscoderFactory);
    mDataFetchSequence=newBitmapCacheGetToDecodeSequence(inputProducer);
  }
  return mDataFetchSequence;
}
