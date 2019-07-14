/** 
 * Returns a sequence that can be used for a prefetch request for an encoded image. <p>Guaranteed to return the same sequence as {@code getEncodedImageProducerSequence(request)}, except that it is pre-pended with a {@link SwallowResultProducer}.
 * @param imageRequest the request that will be submitted
 * @return the sequence that should be used to process the request
 */
public Producer<Void> getEncodedImagePrefetchProducerSequence(ImageRequest imageRequest){
  validateEncodedImageRequest(imageRequest);
switch (imageRequest.getSourceUriType()) {
case SOURCE_TYPE_NETWORK:
    return getNetworkFetchToEncodedMemoryPrefetchSequence();
case SOURCE_TYPE_LOCAL_VIDEO_FILE:
case SOURCE_TYPE_LOCAL_IMAGE_FILE:
  return getLocalFileFetchToEncodedMemoryPrefetchSequence();
default :
final Uri uri=imageRequest.getSourceUri();
throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + getShortenedUriString(uri));
}
}
