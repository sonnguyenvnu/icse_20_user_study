@Override public <R>R create(Class<R> resourceClass){
  return _guiceInjector.getInstance(resourceClass);
}
