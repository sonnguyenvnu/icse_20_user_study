@Override public Iterable<IndexType> getKeyIndexes(){
  if (index == Index.NONE)   return Collections.EMPTY_LIST;
  return ImmutableList.of(indexDef);
}
