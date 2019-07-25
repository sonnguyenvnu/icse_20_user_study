@Override public void prefetch() throws MediaException {
  checkClosed();
  if (state == UNREALIZED) {
    realize();
  }
  if (state == REALIZED) {
    try {
      player.prepare();
      MediaMetadataRetriever retriever=new MediaMetadataRetriever();
      retriever.setDataSource(source.getLocator());
      metadata.updateMetaData(retriever);
      retriever.release();
      state=PREFETCHED;
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
}
