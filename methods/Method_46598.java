@Override public void close() throws SQLException {
  log.debug("transaction type[txc] proxy connection:{} closed.",this);
  SQLException e=null;
  try {
    delegate.close();
  }
 catch (  SQLException sqle) {
    e=sqle;
    throw e;
  }
 finally {
    jdbcEventListener.onAfterConnectionClose(connectionInformation,e);
  }
}
