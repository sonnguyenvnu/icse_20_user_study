Object[] collectActivityLifecycleCallbacks(){
  if (mActivityLifecycleCallbacks == null) {
    return null;
  }
  Object[] callbacks=null;
synchronized (mActivityLifecycleCallbacks) {
    if (mActivityLifecycleCallbacks.size() > 0) {
      callbacks=mActivityLifecycleCallbacks.toArray();
    }
  }
  return callbacks;
}
