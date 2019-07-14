public @Nullable String resolveStringRes(@StringRes int resId){
  if (resId != 0) {
    String cached=mResourceCache.get(resId);
    if (cached != null) {
      return cached;
    }
    String result=mResources.getString(resId);
    mResourceCache.put(resId,result);
    return result;
  }
  return null;
}
