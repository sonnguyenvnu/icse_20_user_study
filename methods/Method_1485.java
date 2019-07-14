/** 
 * Same as  {@code newBitmapCacheGetToBitmapCacheSequence} but with an extra DecodeProducer.
 * @param inputProducer producer providing the input to the decode
 * @return bitmap cache get to decode sequence
 */
private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToDecodeSequence(Producer<EncodedImage> inputProducer){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ProducerSequenceFactory#newBitmapCacheGetToDecodeSequence");
  }
  DecodeProducer decodeProducer=mProducerFactory.newDecodeProducer(inputProducer);
  Producer<CloseableReference<CloseableImage>> result=newBitmapCacheGetToBitmapCacheSequence(decodeProducer);
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return result;
}
