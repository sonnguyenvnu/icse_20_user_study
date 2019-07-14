protected void initCheck() throws SQLException {
  if (JdbcUtils.ORACLE.equals(this.dbType)) {
    isOracle=true;
    if (driver.getMajorVersion() < 10) {
      throw new SQLException("not support oracle driver " + driver.getMajorVersion() + "." + driver.getMinorVersion());
    }
    if (driver.getMajorVersion() == 10 && isUseOracleImplicitCache()) {
      this.getConnectProperties().setProperty("oracle.jdbc.FreeMemoryOnEnterImplicitCache","true");
    }
    oracleValidationQueryCheck();
  }
 else   if (JdbcUtils.DB2.equals(dbType)) {
    db2ValidationQueryCheck();
  }
 else   if (JdbcUtils.MYSQL.equals(this.dbType) || JdbcUtils.MYSQL_DRIVER_6.equals(this.dbType)) {
    isMySql=true;
  }
  if (removeAbandoned) {
    LOG.warn("removeAbandoned is true, not use in productiion.");
  }
}
