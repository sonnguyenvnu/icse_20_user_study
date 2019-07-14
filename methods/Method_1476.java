/** 
 * bitmap cache get -> background thread hand-off -> multiplex -> bitmap cache -> decode -> branch on separate images -> exif resize and rotate -> exif thumbnail creation -> local image resize and rotate -> add meta data producer -> multiplex -> encoded cache -> (webp transcode) -> local file fetch.
 */
private synchronized Producer<CloseableReference<CloseableImage>> getLocalImageFileFetchSequence(){
  if (mLocalImageFileFetchSequence == null) {
    LocalFileFetchProducer localFileFetchProducer=mProducerFactory.newLocalFileFetchProducer();
    mLocalImageFileFetchSequence=newBitmapCacheGetToLocalTransformSequence(localFileFetchProducer);
  }
  return mLocalImageFileFetchSequence;
}
