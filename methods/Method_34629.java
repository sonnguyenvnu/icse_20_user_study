/** 
 * @return Observable if offer accepted, null if batch is full, already started or completed
 */
public Observable<ResponseType> offer(RequestArgumentType arg){
  if (batchStarted.get()) {
    return null;
  }
  if (batchLock.readLock().tryLock()) {
    try {
      if (batchStarted.get()) {
        return null;
      }
      if (argumentMap.size() >= maxBatchSize) {
        return null;
      }
 else {
        CollapsedRequestSubject<ResponseType,RequestArgumentType> collapsedRequest=new CollapsedRequestSubject<ResponseType,RequestArgumentType>(arg,this);
        final CollapsedRequestSubject<ResponseType,RequestArgumentType> existing=(CollapsedRequestSubject<ResponseType,RequestArgumentType>)argumentMap.putIfAbsent(arg,collapsedRequest);
        if (existing != null) {
          boolean requestCachingEnabled=properties.requestCacheEnabled().get();
          if (requestCachingEnabled) {
            return existing.toObservable();
          }
 else {
            return Observable.error(new IllegalArgumentException("Duplicate argument in collapser batch : [" + arg + "]  This is not supported.  Please turn request-caching on for HystrixCollapser:" + commandCollapser.getCollapserKey().name() + " or prevent duplicates from making it into the batch!"));
          }
        }
 else {
          return collapsedRequest.toObservable();
        }
      }
    }
  finally {
      batchLock.readLock().unlock();
    }
  }
 else {
    return null;
  }
}
