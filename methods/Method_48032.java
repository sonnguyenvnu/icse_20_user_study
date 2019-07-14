@Override public RecordIterator<KeyValueEntry> getSlice(KVQuery query,StoreTransaction txh) throws BackendException {
  log.trace("beginning db={}, op=getSlice, tx={}",name,txh);
  final Transaction tx=getTransaction(txh);
  final StaticBuffer keyStart=query.getStart();
  final StaticBuffer keyEnd=query.getEnd();
  final KeySelector selector=query.getKeySelector();
  final List<KeyValueEntry> result=new ArrayList<>();
  final DatabaseEntry foundKey=keyStart.as(ENTRY_FACTORY);
  final DatabaseEntry foundData=new DatabaseEntry();
  try (final Cursor cursor=db.openCursor(tx,null)){
    OperationStatus status=cursor.getSearchKeyRange(foundKey,foundData,getLockMode(txh));
    while (status == OperationStatus.SUCCESS) {
      StaticBuffer key=getBuffer(foundKey);
      if (key.compareTo(keyEnd) >= 0)       break;
      if (selector.include(key)) {
        result.add(new KeyValueEntry(key,getBuffer(foundData)));
      }
      if (selector.reachedLimit())       break;
      status=cursor.getNext(foundKey,foundData,getLockMode(txh));
    }
  }
 catch (  Exception e) {
    throw new PermanentBackendException(e);
  }
  log.trace("db={}, op=getSlice, tx={}, resultcount={}",name,txh,result.size());
  return new RecordIterator<KeyValueEntry>(){
    @Override public boolean hasNext(){
      return entries.hasNext();
    }
    @Override public KeyValueEntry next(){
      return entries.next();
    }
    @Override public void close(){
    }
    @Override public void remove(){
      throw new UnsupportedOperationException();
    }
  }
;
}
