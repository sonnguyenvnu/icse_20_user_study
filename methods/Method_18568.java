synchronized void putCachedValue(Object cachedValueInputs,Object cachedValue){
  if (mCachedValues == null) {
    mCachedValues=new HashMap<>();
  }
  mCachedValues.put(cachedValueInputs,cachedValue);
}
