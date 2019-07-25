@Override public synchronized FileChannel truncate(long newLength) throws IOException {
  if (mode == MapMode.READ_ONLY) {
    throw new NonWritableChannelException();
  }
  if (newLength < size()) {
    setFileLength(newLength);
  }
  return this;
}
