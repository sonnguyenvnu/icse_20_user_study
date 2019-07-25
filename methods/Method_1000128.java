synchronized void collect(Map<WrappedByteArray,WrappedByteArray> all){
  Snapshot next=getRoot().getNext();
  while (next != null) {
    Streams.stream(((SnapshotImpl)next).db).forEach(e -> all.put(WrappedByteArray.of(e.getKey().getBytes()),WrappedByteArray.of(e.getValue().getBytes())));
    next=next.getNext();
  }
}
