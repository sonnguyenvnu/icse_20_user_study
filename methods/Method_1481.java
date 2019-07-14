/** 
 * bitmap cache get -> background thread hand-off -> multiplex -> bitmap cache -> decode -> branch on separate images -> exif resize and rotate -> exif thumbnail creation -> local image resize and rotate -> add meta data producer -> multiplex -> encoded cache -> (webp transcode) -> local asset fetch.
 */
private synchronized Producer<CloseableReference<CloseableImage>> getLocalAssetFetchSequence(){
  if (mLocalAssetFetchSequence == null) {
    LocalAssetFetchProducer localAssetFetchProducer=mProducerFactory.newLocalAssetFetchProducer();
    mLocalAssetFetchSequence=newBitmapCacheGetToLocalTransformSequence(localAssetFetchProducer);
  }
  return mLocalAssetFetchSequence;
}
