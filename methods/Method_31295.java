@Override protected DB2Table[] doAllTables() throws SQLException {
  return findTables("select TABNAME from SYSCAT.TABLES where TYPE='T' and TABSCHEMA = ?",name);
}
