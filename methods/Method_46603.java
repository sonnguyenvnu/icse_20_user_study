@Override public Timestamp getTimestamp(String columnLabel,Calendar cal) throws SQLException {
  SQLException e=null;
  try {
    Timestamp value=delegate.getTimestamp(columnLabel,cal);
    eventListener.onAfterResultSetGet(resultSetInformation,columnLabel,value,null);
    return value;
  }
 catch (  SQLException sqle) {
    e=sqle;
    eventListener.onAfterResultSetGet(resultSetInformation,columnLabel,null,e);
    throw e;
  }
}
