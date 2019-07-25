@Override PairIterator iterator(){
  return new DirectHll4Iterator(1 << lgConfigK);
}
