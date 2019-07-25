@Override public void realize() throws MediaException {
  checkClosed();
  if (state == UNREALIZED) {
    try {
      source.connect();
      player.setDataSource(source.getLocator());
    }
 catch (    IOException e) {
      throw new MediaException(e.getMessage());
    }
    state=REALIZED;
  }
}
