static <T,R>FlatMapCompletable<T,R> create(Func1<T,Completable> func,@Nullable Scheduler scheduler){
  return new FlatMapCompletable<>(checkNotNull(func),scheduler);
}
