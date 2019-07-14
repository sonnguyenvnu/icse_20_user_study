private static Uri createUriForPartialCacheKey(ImageRequest imageRequest){
  return imageRequest.getSourceUri().buildUpon().appendQueryParameter("fresco_partial","true").build();
}
