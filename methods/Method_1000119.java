@Override public void flush(Map<WrappedByteArray,WrappedByteArray> batch){
  batch.forEach((k,v) -> this.put(k.getBytes(),v.getBytes()));
}
