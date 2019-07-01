@Override public synchronized KVIterator<K,V> _XXXXX_(K from,K to){
  checkStoreOpen();
  RangeResultIterator iter=new RangeResultIterator(from,to);
  kvIters.add(iter);
  return iter;
}