public boolean resolveBoolRes(@BoolRes int resId){
  if (resId != 0) {
    Boolean cached=mResourceCache.get(resId);
    if (cached != null) {
      return cached;
    }
    boolean result=mResources.getBoolean(resId);
    mResourceCache.put(resId,result);
    return result;
  }
  return false;
}
