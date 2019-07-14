public int resolveIntRes(@IntegerRes int resId){
  if (resId != 0) {
    Integer cached=mResourceCache.get(resId);
    if (cached != null) {
      return cached;
    }
    int result=mResources.getInteger(resId);
    mResourceCache.put(resId,result);
    return result;
  }
  return 0;
}
