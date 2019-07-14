@Override public int findColumn(String columnLabel) throws SQLException {
  return ((ResultSetMetaDataBase)metaData).findColumn(columnLabel);
}
