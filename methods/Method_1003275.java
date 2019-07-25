@Override public void force(boolean metaData) throws IOException {
  for (int i=0; ; i++) {
    try {
      channel.force(metaData);
      return;
    }
 catch (    IOException e) {
      reopen(i,e);
    }
  }
}
