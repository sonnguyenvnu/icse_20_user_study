@Override public void flush(Map<WrappedByteArray,WrappedByteArray> batch){
  Map<byte[],byte[]> rows=batch.entrySet().stream().map(e -> Maps.immutableEntry(e.getKey().getBytes(),e.getValue().getBytes())).collect(HashMap::new,(m,k) -> m.put(k.getKey(),k.getValue()),HashMap::putAll);
  db.updateByBatch(rows,optionsWrapper);
}
