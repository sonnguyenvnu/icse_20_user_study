@Override public Iterator<Map.Entry<byte[],byte[]>> iterator(){
  Map<WrappedByteArray,WrappedByteArray> all=new HashMap<>();
  collect(all);
  Set<WrappedByteArray> keys=new HashSet<>(all.keySet());
  all.entrySet().removeIf(entry -> entry.getValue() == null || entry.getValue().getBytes() == null);
  return Iterators.concat(Iterators.transform(all.entrySet().iterator(),e -> Maps.immutableEntry(e.getKey().getBytes(),e.getValue().getBytes())),Iterators.filter(getRoot().iterator(),e -> !keys.contains(WrappedByteArray.of(e.getKey()))));
}
