/** 
 * Bitmap cache get -> thread hand off -> multiplex -> bitmap cache
 * @param inputProducer producer providing the input to the bitmap cache
 * @return bitmap cache get to bitmap cache sequence
 */
private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence(Producer<CloseableReference<CloseableImage>> inputProducer){
  BitmapMemoryCacheProducer bitmapMemoryCacheProducer=mProducerFactory.newBitmapMemoryCacheProducer(inputProducer);
  BitmapMemoryCacheKeyMultiplexProducer bitmapKeyMultiplexProducer=mProducerFactory.newBitmapMemoryCacheKeyMultiplexProducer(bitmapMemoryCacheProducer);
  ThreadHandoffProducer<CloseableReference<CloseableImage>> threadHandoffProducer=mProducerFactory.newBackgroundThreadHandoffProducer(bitmapKeyMultiplexProducer,mThreadHandoffProducerQueue);
  return mProducerFactory.newBitmapMemoryCacheGetProducer(threadHandoffProducer);
}
