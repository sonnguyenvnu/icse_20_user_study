/** 
 * bitmap cache get -> background thread hand-off -> multiplex -> bitmap cache -> decode -> branch on separate images -> thumbnail resize and rotate -> thumbnail branch -> local content thumbnail creation -> exif thumbnail creation -> local image resize and rotate -> add meta data producer -> multiplex -> encoded cache -> (webp transcode) -> local content uri fetch.
 */
private synchronized Producer<CloseableReference<CloseableImage>> getLocalContentUriFetchSequence(){
  if (mLocalContentUriFetchSequence == null) {
    LocalContentUriFetchProducer localContentUriFetchProducer=mProducerFactory.newLocalContentUriFetchProducer();
    ThumbnailProducer<EncodedImage>[] thumbnailProducers=new ThumbnailProducer[2];
    thumbnailProducers[0]=mProducerFactory.newLocalContentUriThumbnailFetchProducer();
    thumbnailProducers[1]=mProducerFactory.newLocalExifThumbnailProducer();
    mLocalContentUriFetchSequence=newBitmapCacheGetToLocalTransformSequence(localContentUriFetchProducer,thumbnailProducers);
  }
  return mLocalContentUriFetchSequence;
}
