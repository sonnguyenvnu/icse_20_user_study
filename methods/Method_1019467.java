@Override PairIterator iterator(){
  return new DirectHll6Iterator(1 << lgConfigK);
}
