private void delete(Entry<K,V> entry,RemovalNotification.RemovalReason removalReason){
  assert lruLock.isHeldByCurrentThread();
  if (unlink(entry)) {
    removalListener.onRemoval(new RemovalNotification<>(entry.key,entry.value,removalReason));
  }
}
