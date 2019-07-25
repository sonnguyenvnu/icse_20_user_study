@Override public Observable<R> call(Observable<T> observable){
  return observable.flatMap(new Func1<T,Observable<R>>(){
    @Override public Observable<R> call(    final T value){
      Completable completable=func.call(value);
      if (scheduler != null) {
        completable=completable.subscribeOn(scheduler);
      }
      return completable.toObservable().ignoreElements().map(new Func1<Object,R>(){
        @Override public R call(        Object ignored){
          throw new IllegalStateException("Impossible state! ignoreElements() mustn't allow values to be emitted!");
        }
      }
);
    }
  }
);
}
