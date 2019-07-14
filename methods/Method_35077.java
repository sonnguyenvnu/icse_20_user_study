Observable<Parsed> readDisk(@Nonnull final Key key){
  return persister().read(key).doOnNext(this::guardAgainstEmptyCollection).onErrorResumeNext(Observable.empty()).doOnNext(parsed -> {
    updateMemory(key,parsed);
    if (stalePolicy == StalePolicy.REFRESH_ON_STALE && StoreUtil.persisterIsStale(key,persister)) {
      backfillCache(key);
    }
  }
).cache();
}
