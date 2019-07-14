@Nullable @VisibleForTesting static Map<String,String> getExtraMap(final ProducerListener listener,final String requestId,final boolean valueFound,final int sizeInBytes){
  if (!listener.requiresExtraMap(requestId)) {
    return null;
  }
  if (valueFound) {
    return ImmutableMap.of(EXTRA_CACHED_VALUE_FOUND,String.valueOf(valueFound),ENCODED_IMAGE_SIZE,String.valueOf(sizeInBytes));
  }
 else {
    return ImmutableMap.of(EXTRA_CACHED_VALUE_FOUND,String.valueOf(valueFound));
  }
}
