/** 
 * Decreases the reference count of live object from the static map. Removes it if it's reference count has become 0.
 * @param value the value to remove.
 */
private static void removeLiveReference(Object value){
synchronized (sLiveObjects) {
    Integer count=sLiveObjects.get(value);
    if (count == null) {
      FLog.wtf("SharedReference","No entry in sLiveObjects for value of type %s",value.getClass());
    }
 else     if (count == 1) {
      sLiveObjects.remove(value);
    }
 else {
      sLiveObjects.put(value,count - 1);
    }
  }
}
