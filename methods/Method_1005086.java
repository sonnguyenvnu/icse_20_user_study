@Override public CloseableIterator<Element> iterator(){
  close();
  if (null != ids) {
    idsIterator=ids.iterator();
    iterator=new HBaseRetrieverIterable(new BatchedResultScanner()).iterator();
  }
 else {
    iterator=new HBaseRetrieverIterable(createScanner()).iterator();
  }
  return iterator;
}
