@Override public void addBatch() throws SQLException {
  SQLException e=null;
  long start=System.nanoTime();
  try {
    eventListener.onBeforeAddBatch(statementInformation);
    delegate.addBatch();
  }
 catch (  SQLException sqle) {
    e=sqle;
    throw e;
  }
 finally {
    eventListener.onAfterAddBatch(statementInformation,System.nanoTime() - start,e);
  }
}
