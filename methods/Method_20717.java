@Override @NonNull public Observable<T> call(final @NonNull Observable<T> source){
  return source.doOnError(e -> {
    if (this.errorAction != null) {
      this.errorAction.call(e);
    }
  }
).onErrorResumeNext(Observable.empty());
}
