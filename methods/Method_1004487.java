public <E>void subscribe(final Class<E> clazz,final Listener<E> listener){
  final Lock lock=guard.writeLock();
  lock.lock();
  try {
    listenersByType.put(clazz,listener);
  }
  finally {
    lock.unlock();
  }
}
