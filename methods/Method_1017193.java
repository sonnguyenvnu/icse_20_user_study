private void start(final String id,final LifeCycleHook<AsyncFuture<Void>> starter){
  starters.add(new Hook(id,starter));
}
