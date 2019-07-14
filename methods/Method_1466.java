/** 
 * Returns a sequence that can be used for a request for an encoded image from either network or local files.
 * @param imageRequest the request that will be submitted
 * @return the sequence that should be used to process the request
 */
public Producer<CloseableReference<PooledByteBuffer>> getEncodedImageProducerSequence(ImageRequest imageRequest){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("ProducerSequenceFactory#getEncodedImageProducerSequence");
    }
    validateEncodedImageRequest(imageRequest);
    final Uri uri=imageRequest.getSourceUri();
switch (imageRequest.getSourceUriType()) {
case SOURCE_TYPE_NETWORK:
      return getNetworkFetchEncodedImageProducerSequence();
case SOURCE_TYPE_LOCAL_VIDEO_FILE:
case SOURCE_TYPE_LOCAL_IMAGE_FILE:
    return getLocalFileFetchEncodedImageProducerSequence();
case SOURCE_TYPE_LOCAL_CONTENT:
  return getLocalContentUriFetchEncodedImageProducerSequence();
default :
throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + getShortenedUriString(uri));
}
}
  finally {
if (FrescoSystrace.isTracing()) {
FrescoSystrace.endSection();
}
}
}
