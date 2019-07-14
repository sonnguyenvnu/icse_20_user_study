@Override public boolean onClick(){
  if (FLog.isLoggable(FLog.VERBOSE)) {
    FLog.v(TAG,"controller %x %s: onClick",System.identityHashCode(this),mId);
  }
  if (shouldRetryOnTap()) {
    mRetryManager.notifyTapToRetry();
    mSettableDraweeHierarchy.reset();
    submitRequest();
    return true;
  }
  return false;
}
