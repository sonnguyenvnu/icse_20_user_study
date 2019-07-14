@Override public @NonNull Observable<T> call(final @NonNull Observable<T> source){
  return source.doOnError(e -> {
    final ErrorEnvelope env=ErrorEnvelope.fromThrowable(e);
    if (env != null && this.errorAction != null) {
      this.errorAction.call(env);
    }
  }
).onErrorResumeNext(e -> {
    if (ErrorEnvelope.fromThrowable(e) == null) {
      return Observable.error(e);
    }
 else {
      return Observable.empty();
    }
  }
);
}
