public void removeObserver(Object observer,int id){
  if (BuildVars.DEBUG_VERSION) {
    if (Thread.currentThread() != ApplicationLoader.applicationHandler.getLooper().getThread()) {
      throw new RuntimeException("removeObserver allowed only from MAIN thread");
    }
  }
  if (broadcasting != 0) {
    ArrayList<Object> arrayList=removeAfterBroadcast.get(id);
    if (arrayList == null) {
      arrayList=new ArrayList<>();
      removeAfterBroadcast.put(id,arrayList);
    }
    arrayList.add(observer);
    return;
  }
  ArrayList<Object> objects=observers.get(id);
  if (objects != null) {
    objects.remove(observer);
  }
}
