@Override public void next(Registry registry){
  requestConstants.indexes.peek().registry=getCurrentRegistry().join(registry);
  next();
}
