/** 
 * Returns a sequence that can be used for a prefetch request for a decoded image.
 * @param imageRequest the request that will be submitted
 * @return the sequence that should be used to process the request
 */
public Producer<Void> getDecodedImagePrefetchProducerSequence(ImageRequest imageRequest){
  Producer<CloseableReference<CloseableImage>> inputProducer=getBasicDecodedImageSequence(imageRequest);
  if (mUseBitmapPrepareToDraw) {
    inputProducer=getBitmapPrepareSequence(inputProducer);
  }
  return getDecodedImagePrefetchSequence(inputProducer);
}
