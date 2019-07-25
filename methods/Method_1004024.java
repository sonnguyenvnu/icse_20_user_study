@Override public Optional<Listener> acquire(ContextT context){
  return tryAcquire(context).map(delegate -> new Listener(){
    @Override public void onSuccess(){
      delegate.onSuccess();
      unblock();
    }
    @Override public void onIgnore(){
      delegate.onIgnore();
      unblock();
    }
    @Override public void onDropped(){
      delegate.onDropped();
      unblock();
    }
  }
);
}
