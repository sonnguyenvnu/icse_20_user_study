public static <T,E>LifecycleTransformer<T> bindToLifeCycle(Observable<E> lifecycle,final Function<E,E> correspondingEvents){
  Observable<E> lifecycleCopy=lifecycle.share();
  return new LifecycleTransformer<>(Observable.combineLatest(lifecycle.take(1).map(correspondingEvents),lifecycleCopy.skip(1),new BiFunction<E,E,Boolean>(){
    @Override public Boolean apply(    E e,    E e2) throws Exception {
      return e.equals(e2);
    }
  }
).filter(new Predicate<Boolean>(){
    @Override public boolean test(    Boolean cmpResult) throws Exception {
      return cmpResult;
    }
  }
));
}
