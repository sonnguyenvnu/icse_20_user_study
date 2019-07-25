@Override public Iterator<Map.Entry<byte[],byte[]>> iterator(){
  return Iterators.transform(db.entrySet().iterator(),e -> Maps.immutableEntry(e.getKey().getBytes(),Longs.toByteArray(e.getValue())));
}
