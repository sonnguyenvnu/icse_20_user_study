@Override public Lifecycle.Event peekLifecycle(){
  lifecycleObservable.backfillEvents();
  return lifecycleObservable.getValue();
}
