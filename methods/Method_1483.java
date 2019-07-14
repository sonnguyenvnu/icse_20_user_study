/** 
 * Creates a new fetch sequence that just needs the source producer.
 * @param inputProducer the source producer
 * @return the new sequence
 */
private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> inputProducer){
  ThumbnailProducer<EncodedImage>[] defaultThumbnailProducers=new ThumbnailProducer[1];
  defaultThumbnailProducers[0]=mProducerFactory.newLocalExifThumbnailProducer();
  return newBitmapCacheGetToLocalTransformSequence(inputProducer,defaultThumbnailProducers);
}
