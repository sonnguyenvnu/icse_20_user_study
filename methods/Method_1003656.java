public void insert(final Registry registry,final Handler... handlers){
  if (handlers.length == 0) {
    throw new IllegalArgumentException("handlers is zero length");
  }
  requestConstants.indexes.push(new ChainIndex(handlers,getCurrentRegistry().join(registry),false));
  next();
}
