@NotNull @Override public RealApolloQueryWatcher<T> watcher(){
  return new RealApolloQueryWatcher<>(clone(),apolloStore,logger,tracker);
}
