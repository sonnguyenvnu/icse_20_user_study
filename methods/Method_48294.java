@Override public KeySliceQuery updateLimit(int newLimit){
  return new KeySliceQuery(key,this).setLimit(newLimit);
}
