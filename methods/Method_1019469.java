@Override PairIterator iterator(){
  return new DirectHll8Iterator(1 << lgConfigK);
}
