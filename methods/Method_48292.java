@Override public KeyRangeQuery updateLimit(int newLimit){
  return new KeyRangeQuery(keyStart,keyEnd,this).setLimit(newLimit);
}
