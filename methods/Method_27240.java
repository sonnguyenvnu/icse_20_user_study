public static <T>Observable<T> getObservable(@NonNull Observable<T> observable){
  return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
}
