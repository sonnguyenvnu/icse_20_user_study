/** 
 * Increases the reference count of a live object in the static map. Adds it if it's not being held.
 * @param value the value to add.
 */
private static void addLiveReference(Object value){
  if (CloseableReference.useGc() && (value instanceof Bitmap || value instanceof HasBitmap)) {
    return;
  }
synchronized (sLiveObjects) {
    Integer count=sLiveObjects.get(value);
    if (count == null) {
      sLiveObjects.put(value,1);
    }
 else {
      sLiveObjects.put(value,count + 1);
    }
  }
}
