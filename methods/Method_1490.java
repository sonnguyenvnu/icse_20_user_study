/** 
 * post-processor producer -> copy producer -> inputProducer
 */
private synchronized Producer<CloseableReference<CloseableImage>> getPostprocessorSequence(Producer<CloseableReference<CloseableImage>> inputProducer){
  if (!mPostprocessorSequences.containsKey(inputProducer)) {
    PostprocessorProducer postprocessorProducer=mProducerFactory.newPostprocessorProducer(inputProducer);
    PostprocessedBitmapMemoryCacheProducer postprocessedBitmapMemoryCacheProducer=mProducerFactory.newPostprocessorBitmapMemoryCacheProducer(postprocessorProducer);
    mPostprocessorSequences.put(inputProducer,postprocessedBitmapMemoryCacheProducer);
  }
  return mPostprocessorSequences.get(inputProducer);
}
