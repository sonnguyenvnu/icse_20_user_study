@Nullable public String[] resolveStringArrayRes(@ArrayRes int resId){
  if (resId != 0) {
    String[] cached=mResourceCache.get(resId);
    if (cached != null) {
      return cached;
    }
    String[] result=mResources.getStringArray(resId);
    mResourceCache.put(resId,result);
    return result;
  }
  return null;
}
