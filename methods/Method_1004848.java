@Override protected RowMap evict() throws IOException {
  RowMap r=super.evict();
  this.memorySize-=r.getApproximateSize();
  this.outputStreamCacheSize+=r.getApproximateSize();
  if (this.outputStreamCacheSize > FlushOutputStreamBytes) {
    resetOutputStreamCaches();
    this.outputStreamCacheSize=0;
  }
  return r;
}
