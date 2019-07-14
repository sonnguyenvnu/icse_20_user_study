@Override public @NonNull Observable<Location> fetchLocation(final @NonNull String param){
  return Observable.just(LocationFactory.sydney());
}
