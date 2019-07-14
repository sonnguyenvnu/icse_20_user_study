@Override public @NonNull Observable<Category> fetchCategory(final @NonNull String id){
  return this.service.category(id).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
