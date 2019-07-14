@Nullable synchronized Object getCachedValue(Object cachedValueInputs){
  if (mCachedValues == null) {
    mCachedValues=new HashMap<>();
  }
  return mCachedValues.get(cachedValueInputs);
}
