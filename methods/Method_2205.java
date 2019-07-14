public synchronized String getUriString(CacheKey key){
  ImageDebugData imageDebugData=getImageDebugData(key);
  if (imageDebugData != null) {
    ImageRequest imageRequest=imageDebugData.getImageRequest();
    if (imageRequest != null) {
      return imageRequest.getSourceUri().toString();
    }
  }
  return key.getUriString();
}
