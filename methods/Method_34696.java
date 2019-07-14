public Observable<R> toObservableWithStateCopiedInto(final AbstractCommand<R> commandToCopyStateInto){
  final AtomicBoolean completionLogicRun=new AtomicBoolean(false);
  return cachedObservable.doOnError(new Action1<Throwable>(){
    @Override public void call(    Throwable throwable){
      if (completionLogicRun.compareAndSet(false,true)) {
        commandCompleted(commandToCopyStateInto);
      }
    }
  }
).doOnCompleted(new Action0(){
    @Override public void call(){
      if (completionLogicRun.compareAndSet(false,true)) {
        commandCompleted(commandToCopyStateInto);
      }
    }
  }
).doOnUnsubscribe(new Action0(){
    @Override public void call(){
      if (completionLogicRun.compareAndSet(false,true)) {
        commandUnsubscribed(commandToCopyStateInto);
      }
    }
  }
);
}
