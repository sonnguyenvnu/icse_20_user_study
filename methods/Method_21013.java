@Override public @NonNull Observable<Location> fetchLocation(final @NonNull String param){
  return this.service.location(param).subscribeOn(Schedulers.io()).lift(apiErrorOperator());
}
