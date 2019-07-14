public synchronized ImageDebugData trackImage(CacheKey key){
  ImageDebugData data=new ImageDebugData(null);
  mImageDebugDataMap.put(key,data);
  return data;
}
