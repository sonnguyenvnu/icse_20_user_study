@SuppressWarnings("unchecked") @Override public <T>T get(Object key,Callable<T> valueLoader){
  Object value=lookup(key);
  if (value != null) {
    return (T)value;
  }
  ReentrantLock lock=keyLockMap.get(key.toString());
  if (lock == null) {
    logger.debug("?key??? : {}",key);
    lock=new ReentrantLock();
    keyLockMap.putIfAbsent(key.toString(),lock);
  }
  try {
    lock.lock();
    value=lookup(key);
    if (value != null) {
      return (T)value;
    }
    value=valueLoader.call();
    Object storeValue=toStoreValue(value);
    put(key,storeValue);
    return (T)value;
  }
 catch (  Exception e) {
    throw new ValueRetrievalException(key,valueLoader,e.getCause());
  }
 finally {
    lock.unlock();
  }
}
