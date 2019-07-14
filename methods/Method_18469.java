@Nullable public final int[] resolveIntArrayRes(@ArrayRes int resId){
  if (resId != 0) {
    int[] cached=mResourceCache.get(resId);
    if (cached != null) {
      return cached;
    }
    int[] result=mResources.getIntArray(resId);
    mResourceCache.put(resId,result);
    return result;
  }
  return null;
}
