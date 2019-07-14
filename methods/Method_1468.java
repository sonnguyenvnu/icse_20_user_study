private static void validateEncodedImageRequest(ImageRequest imageRequest){
  Preconditions.checkNotNull(imageRequest);
  Preconditions.checkArgument(imageRequest.getLowestPermittedRequestLevel().getValue() <= ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue());
}
