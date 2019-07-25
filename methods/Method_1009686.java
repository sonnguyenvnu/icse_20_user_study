@Override public WriteBatchImpl put(byte[] key,byte[] value){
  requireNonNull(key,"key is null");
  requireNonNull(value,"value is null");
  batch.add(Maps.immutableEntry(Slices.wrappedBuffer(key),Slices.wrappedBuffer(value)));
  approximateSize+=12 + key.length + value.length;
  return this;
}
