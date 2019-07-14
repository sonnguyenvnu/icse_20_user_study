@Override public Iterator<Entry> iterator(){
  Iterator<Entry> iterator;
  if (sliceQuery.hasLimit() && sliceQuery.getLimit() != query.getLimit()) {
    iterator=new LimitAdjustingIterator();
  }
 else {
    iterator=getBasicIterator();
  }
  return iterator;
}
