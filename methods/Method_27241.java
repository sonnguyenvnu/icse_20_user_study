public static <T>Observable<T> safeObservable(@NonNull Observable<T> observable){
  return getObservable(observable).doOnError(Throwable::printStackTrace);
}
