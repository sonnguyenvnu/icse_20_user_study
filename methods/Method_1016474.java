@Override public final synchronized long available() throws IOException {
  checkReopen();
  return this.length() - this.RAFile.getFilePointer();
}
