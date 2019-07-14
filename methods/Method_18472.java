public int resolveColorRes(@ColorRes int resId){
  if (resId != 0) {
    Integer cached=mResourceCache.get(resId);
    if (cached != null) {
      return cached;
    }
    int result=mResources.getColor(resId);
    mResourceCache.put(resId,result);
    return result;
  }
  return 0;
}
