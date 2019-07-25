@Override public void init(DataSource dataSource) throws Exception {
  super.init(dataSource);
  String version=JdbcUtils.extractDatabaseMetaData(dataSource,"getDatabaseProductVersion").toString();
  if (!isDerbyVersionSupported(version)) {
    throw new InvalidDataAccessResourceUsageException("Apache Derby version " + version + " is not supported by this class,  Only version " + MINIMAL_DERBY_VERSION + " or later is supported");
  }
}
