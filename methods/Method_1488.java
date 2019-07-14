/** 
 * Branch on separate images -> thumbnail resize and rotate -> thumbnail producers as provided -> local image resize and rotate -> add meta data producer
 * @param inputProducer producer providing the input to add meta data producer
 * @param thumbnailProducers the thumbnail producers from which to request the image beforefalling back to the full image producer sequence
 * @return local transformations sequence
 */
private Producer<EncodedImage> newLocalTransformationsSequence(Producer<EncodedImage> inputProducer,ThumbnailProducer<EncodedImage>[] thumbnailProducers){
  Producer<EncodedImage> localImageProducer=ProducerFactory.newAddImageTransformMetaDataProducer(inputProducer);
  localImageProducer=mProducerFactory.newResizeAndRotateProducer(localImageProducer,true,mImageTranscoderFactory);
  ThrottlingProducer<EncodedImage> localImageThrottlingProducer=mProducerFactory.newThrottlingProducer(localImageProducer);
  return mProducerFactory.newBranchOnSeparateImagesProducer(newLocalThumbnailProducer(thumbnailProducers),localImageThrottlingProducer);
}
