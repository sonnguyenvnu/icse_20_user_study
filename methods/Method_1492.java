/** 
 * bitmap prepare producer -> inputProducer
 */
private synchronized Producer<CloseableReference<CloseableImage>> getBitmapPrepareSequence(Producer<CloseableReference<CloseableImage>> inputProducer){
  Producer<CloseableReference<CloseableImage>> bitmapPrepareProducer=mBitmapPrepareSequences.get(inputProducer);
  if (bitmapPrepareProducer == null) {
    bitmapPrepareProducer=mProducerFactory.newBitmapPrepareProducer(inputProducer);
    mBitmapPrepareSequences.put(inputProducer,bitmapPrepareProducer);
  }
  return bitmapPrepareProducer;
}
