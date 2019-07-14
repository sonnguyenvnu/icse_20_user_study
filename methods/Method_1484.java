/** 
 * Creates a new fetch sequence that just needs the source producer.
 * @param inputProducer the source producer
 * @param thumbnailProducers the thumbnail producers from which to request the image beforefalling back to the full image producer sequence
 * @return the new sequence
 */
private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> inputProducer,ThumbnailProducer<EncodedImage>[] thumbnailProducers){
  inputProducer=newEncodedCacheMultiplexToTranscodeSequence(inputProducer);
  Producer<EncodedImage> inputProducerAfterDecode=newLocalTransformationsSequence(inputProducer,thumbnailProducers);
  return newBitmapCacheGetToDecodeSequence(inputProducerAfterDecode);
}
