public Throwable doLock(){
  Throwable lockError=null;
  for (  Map.Entry<String,L> lock : lockStore.entrySet()) {
    try {
      boolean success=lockAccepter.accept(lock.getValue());
      if (!success) {
        return new TimeoutException("try lock " + lock.getKey() + " error");
      }
      successLock.add(lock.getValue());
    }
 catch (    Throwable throwable) {
      lockError=throwable;
    }
  }
  return lockError;
}
