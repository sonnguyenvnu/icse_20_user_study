@Override public void close() throws IOException {
  if (opened) {
    opened=false;
    transferEnded();
  }
  uri=null;
}
