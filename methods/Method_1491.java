/** 
 * swallow result producer -> inputProducer
 */
private synchronized Producer<Void> getDecodedImagePrefetchSequence(Producer<CloseableReference<CloseableImage>> inputProducer){
  if (!mCloseableImagePrefetchSequences.containsKey(inputProducer)) {
    SwallowResultProducer<CloseableReference<CloseableImage>> swallowResultProducer=mProducerFactory.newSwallowResultProducer(inputProducer);
    mCloseableImagePrefetchSequences.put(inputProducer,swallowResultProducer);
  }
  return mCloseableImagePrefetchSequences.get(inputProducer);
}
