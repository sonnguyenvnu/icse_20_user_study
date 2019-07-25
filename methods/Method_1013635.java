@Override public long out(WritableByteChannel writableByteChannel) throws IOException {
  long size=doOut(writableByteChannel);
  outSize.addAndGet(size);
  return size;
}
