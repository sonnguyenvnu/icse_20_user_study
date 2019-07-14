@Override public @NonNull Observable<Category> fetchCategory(final @NonNull String param){
  return Observable.just(CategoryFactory.musicCategory());
}
