private void stop(final String id,final LifeCycleHook<AsyncFuture<Void>> stopper){
  stoppers.add(new Hook(id,stopper));
}
