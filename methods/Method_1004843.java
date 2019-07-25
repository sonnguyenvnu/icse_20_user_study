public static MysqlVersion capture(Connection c) throws SQLException {
  DatabaseMetaData meta=c.getMetaData();
  return new MysqlVersion(meta.getDatabaseMajorVersion(),meta.getDatabaseMinorVersion());
}
