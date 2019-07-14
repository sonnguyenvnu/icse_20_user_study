/** 
 * bitmap cache get -> background thread hand-off -> multiplex -> bitmap cache -> decode -> branch on separate images -> exif resize and rotate -> exif thumbnail creation -> local image resize and rotate -> add meta data producer -> multiplex -> encoded cache -> (webp transcode) -> qualified resource fetch.
 */
private synchronized Producer<CloseableReference<CloseableImage>> getQualifiedResourceFetchSequence(){
  if (mQualifiedResourceFetchSequence == null) {
    QualifiedResourceFetchProducer qualifiedResourceFetchProducer=mProducerFactory.newQualifiedResourceFetchProducer();
    mQualifiedResourceFetchSequence=newBitmapCacheGetToLocalTransformSequence(qualifiedResourceFetchProducer);
  }
  return mQualifiedResourceFetchSequence;
}
