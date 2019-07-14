/** 
 * Bitmap cache get -> thread hand off -> multiplex -> bitmap cache -> local video thumbnail
 */
private synchronized Producer<CloseableReference<CloseableImage>> getLocalVideoFileFetchSequence(){
  if (mLocalVideoFileFetchSequence == null) {
    LocalVideoThumbnailProducer localVideoThumbnailProducer=mProducerFactory.newLocalVideoThumbnailProducer();
    mLocalVideoFileFetchSequence=newBitmapCacheGetToBitmapCacheSequence(localVideoThumbnailProducer);
  }
  return mLocalVideoFileFetchSequence;
}
