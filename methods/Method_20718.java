@Override public @NonNull Observable<T> call(final @NonNull Observable<T> source){
  return source.flatMap(value -> {
    if (ThreadUtils.isMainThread()) {
      return Observable.just(value).observeOn(Schedulers.immediate());
    }
 else {
      return Observable.just(value).observeOn(AndroidSchedulers.mainThread());
    }
  }
);
}
