@Nonnull public Observable<Parsed> memory(@Nonnull Key key){
  Observable<Parsed> cachedValue=memCache.getIfPresent(key);
  return cachedValue == null ? Observable.empty() : cachedValue;
}
