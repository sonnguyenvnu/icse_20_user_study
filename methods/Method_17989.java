void putCachedValue(Object cachedValueInputs,Object cachedValue){
  if (isReleased()) {
    return;
  }
  mStateHandler.putCachedValue(cachedValueInputs,cachedValue);
}
