protected long getIdUpperBound(final int idNamespace){
  Preconditions.checkArgument(blockSizer != null,"Blocksizer has not yet been initialized");
  isActive=true;
  long upperBound=blockSizer.getIdUpperBound(idNamespace);
  Preconditions.checkArgument(upperBound > 0,"Invalid upper bound: %s",upperBound);
  return upperBound;
}
