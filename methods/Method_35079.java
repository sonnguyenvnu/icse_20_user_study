@Nonnull Observable<Parsed> response(@Nonnull final Key key){
  return fetcher().fetch(key).doOnSuccess(it -> persister().write(key,it)).flatMapObservable(it -> readDisk(key)).onErrorResumeNext(throwable -> {
    if (stalePolicy == StalePolicy.NETWORK_BEFORE_STALE) {
      return readDisk(key).switchIfEmpty(Observable.error(throwable));
    }
    return Observable.error(throwable);
  }
).doAfterTerminate(() -> inFlightRequests.invalidate(key)).cache();
}
