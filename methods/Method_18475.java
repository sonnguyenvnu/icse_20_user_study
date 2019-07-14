public float resolveFloatRes(@DimenRes int resId){
  if (resId != 0) {
    Float cached=mResourceCache.get(resId);
    if (cached != null) {
      return cached;
    }
    float result=mResources.getDimension(resId);
    mResourceCache.put(resId,result);
    return result;
  }
  return 0;
}
