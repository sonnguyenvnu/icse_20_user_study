@Override public FileChannel position(long pos) throws IOException {
  for (int i=0; ; i++) {
    try {
      channel.position(pos);
      return this;
    }
 catch (    IOException e) {
      reopen(i,e);
    }
  }
}
