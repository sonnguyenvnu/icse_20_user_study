@Override public void start(Resolver resolver){
  if (!shouldStart()) {
    notifyCanceledBeforeStart();
    return;
  }
  notifyWillStart();
  setupBinding(resolver);
  mGraphBinding.activate();
}
