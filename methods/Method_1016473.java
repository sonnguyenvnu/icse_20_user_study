@Override public final synchronized long available() throws IOException {
  return this.length() - RAFile.getFilePointer();
}
