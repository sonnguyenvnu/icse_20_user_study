/** 
 * Associates the object with the specified key and puts it into the  {@link BucketMap}. Does not overwrite the previous object, if any.
 * @param key
 */
public synchronized void release(int key,T value){
  LinkedEntry<T> bucket=mMap.get(key);
  if (bucket == null) {
    bucket=new LinkedEntry<T>(null,key,new LinkedList<T>(),null);
    mMap.put(key,bucket);
  }
  bucket.value.addLast(value);
  moveToFront(bucket);
}
