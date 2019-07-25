@Override public void close() throws SQLException {
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
