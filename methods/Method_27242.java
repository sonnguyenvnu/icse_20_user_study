public static <T>Single<T> getSingle(@NonNull Single<T> single){
  return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
}
