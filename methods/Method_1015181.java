@Override public LinearMap<K,V> clone(){
  LinearMap<K,V> m=new LinearMap<K,V>(table.length,entries.length,hashFn,equalsFn);
  m.size=size;
  m.indexMask=indexMask;
  arraycopy(table,0,m.table,0,table.length);
  arraycopy(entries,0,m.entries,0,size << 1);
  return m;
}
