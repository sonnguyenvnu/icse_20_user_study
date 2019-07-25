@Override public <R>R create(final Class<R> resourceClass){
  return _containerAdaptor.getBean(resourceClass);
}
