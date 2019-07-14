/** 
 * Creates a new reference for the client. 
 */
private synchronized CloseableReference<V> newClientReference(final Entry<K,V> entry){
  increaseClientCount(entry);
  return CloseableReference.of(entry.valueRef.get(),new ResourceReleaser<V>(){
    @Override public void release(    V unused){
      releaseClientReference(entry);
    }
  }
);
}
