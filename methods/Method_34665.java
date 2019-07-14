/** 
 * A lazy  {@link Observable} that will execute when subscribed to.<p> See https://github.com/Netflix/RxJava/wiki for more information.
 * @param observeOn The  {@link Scheduler} to execute callbacks on.
 * @return {@code Observable<R>} that lazily executes and calls back with the result of of {@link HystrixCommand}{@code <BatchReturnType>} execution after passing through{@link #mapResponseToRequests} to transform the {@code <BatchReturnType>} into {@code <ResponseType>}
 */
public Observable<ResponseType> toObservable(Scheduler observeOn){
  return Observable.defer(new Func0<Observable<ResponseType>>(){
    @Override public Observable<ResponseType> call(){
      final boolean isRequestCacheEnabled=getProperties().requestCacheEnabled().get();
      final String cacheKey=getCacheKey();
      if (isRequestCacheEnabled) {
        HystrixCachedObservable<ResponseType> fromCache=requestCache.get(cacheKey);
        if (fromCache != null) {
          metrics.markResponseFromCache();
          return fromCache.toObservable();
        }
      }
      RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType> requestCollapser=collapserFactory.getRequestCollapser(collapserInstanceWrapper);
      Observable<ResponseType> response=requestCollapser.submitRequest(getRequestArgument());
      if (isRequestCacheEnabled && cacheKey != null) {
        HystrixCachedObservable<ResponseType> toCache=HystrixCachedObservable.from(response);
        HystrixCachedObservable<ResponseType> fromCache=requestCache.putIfAbsent(cacheKey,toCache);
        if (fromCache == null) {
          return toCache.toObservable();
        }
 else {
          toCache.unsubscribe();
          return fromCache.toObservable();
        }
      }
      return response;
    }
  }
);
}
