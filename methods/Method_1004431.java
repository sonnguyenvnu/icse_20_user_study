@Override public long validate() throws IOException {
  long size=this.fileChannel.size();
  long invalidateBytes=size % Long.BYTES;
  return size - invalidateBytes;
}
