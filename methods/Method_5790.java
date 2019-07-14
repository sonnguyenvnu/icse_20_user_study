@Override public void close() throws IOException {
  if (data != null) {
    data=null;
    transferEnded();
  }
  dataSpec=null;
}
