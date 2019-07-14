@Override public Ref getRef(String columnLabel) throws SQLException {
  SQLException e=null;
  try {
    Ref value=delegate.getRef(columnLabel);
    eventListener.onAfterResultSetGet(resultSetInformation,columnLabel,value,null);
    return value;
  }
 catch (  SQLException sqle) {
    e=sqle;
    eventListener.onAfterResultSetGet(resultSetInformation,columnLabel,null,e);
    throw e;
  }
}
