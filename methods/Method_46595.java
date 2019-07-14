@Override public void setTimestamp(String parameterName,Timestamp x) throws SQLException {
  SQLException e=null;
  try {
    delegate.setTimestamp(parameterName,x);
  }
 catch (  SQLException sqle) {
    e=sqle;
    throw e;
  }
 finally {
    eventListener.onAfterCallableStatementSet(statementInformation,parameterName,x,e);
  }
}
