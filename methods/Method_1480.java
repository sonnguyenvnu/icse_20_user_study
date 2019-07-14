/** 
 * bitmap cache get -> background thread hand-off -> multiplex -> bitmap cache -> decode -> branch on separate images -> exif resize and rotate -> exif thumbnail creation -> local image resize and rotate -> add meta data producer -> multiplex -> encoded cache -> (webp transcode) -> local resource fetch.
 */
private synchronized Producer<CloseableReference<CloseableImage>> getLocalResourceFetchSequence(){
  if (mLocalResourceFetchSequence == null) {
    LocalResourceFetchProducer localResourceFetchProducer=mProducerFactory.newLocalResourceFetchProducer();
    mLocalResourceFetchSequence=newBitmapCacheGetToLocalTransformSequence(localResourceFetchProducer);
  }
  return mLocalResourceFetchSequence;
}
