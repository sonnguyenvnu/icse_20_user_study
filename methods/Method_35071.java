@Override public Observable<Parsed> getRefreshing(@Nonnull Key key){
  return internalStore.getRefreshing(key);
}
