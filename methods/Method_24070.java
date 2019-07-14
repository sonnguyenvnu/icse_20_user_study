@Override @SuppressWarnings("rawtypes") public Object getCache(PImage image){
  Object storage=getPrimaryPG().cacheMap.get(image);
  if (storage != null && storage.getClass() == WeakReference.class) {
    return ((WeakReference)storage).get();
  }
  return storage;
}
