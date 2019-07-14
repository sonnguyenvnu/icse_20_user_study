@Override public synchronized void trackImage(ImageRequest imageRequest,CacheKey cacheKey){
  ImageDebugData imageDebugData=mImageRequestDebugDataMap.get(imageRequest);
  if (imageDebugData == null) {
    imageDebugData=new ImageDebugData(imageRequest);
    mImageDebugDataMap.put(cacheKey,imageDebugData);
    mImageRequestDebugDataMap.put(imageRequest,imageDebugData);
  }
  imageDebugData.addCacheKey(cacheKey);
  imageDebugData.addResourceId(CacheKeyUtil.getFirstResourceId(cacheKey));
}
