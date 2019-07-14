/** 
 * Executes ALTER statements for all tables that have Flashback Archive enabled. Flashback Archive is an asynchronous process so we need to wait until it completes, otherwise cleaning the tables in schema will sometimes fail with ORA-55622 or ORA-55610 depending on the race between Flashback Archive and Java code.
 * @throws SQLException when the statements could not be generated.
 */
private void disableFlashbackArchiveForFbaTrackedTables() throws SQLException {
  boolean dbaViewAccessible=database.isPrivOrRoleGranted("SELECT ANY DICTIONARY") || database.isDataDictViewAccessible("DBA_FLASHBACK_ARCHIVE_TABLES");
  if (!dbaViewAccessible && !isDefaultSchemaForUser()) {
    LOG.warn("Unable to check and disable Flashback Archive for tables in schema " + database.quote(name) + " by user \"" + database.doGetCurrentUser() + "\": DBA_FLASHBACK_ARCHIVE_TABLES is not accessible");
    return;
  }
  boolean oracle18orNewer=database.getVersion().isAtLeast("18");
  String queryForFbaTrackedTables="SELECT TABLE_NAME FROM " + (dbaViewAccessible ? "DBA_" : "USER_") + "FLASHBACK_ARCHIVE_TABLES WHERE OWNER_NAME = ?" + (oracle18orNewer ? " AND STATUS='ENABLED'" : "");
  List<String> tableNames=jdbcTemplate.queryForStringList(queryForFbaTrackedTables,name);
  for (  String tableName : tableNames) {
    jdbcTemplate.execute("ALTER TABLE " + database.quote(name,tableName) + " NO FLASHBACK ARCHIVE");
    while (database.queryReturnsRows(queryForFbaTrackedTables + " AND TABLE_NAME = ?",name,tableName)) {
      try {
        LOG.debug("Actively waiting for Flashback cleanup on table: " + database.quote(name,tableName));
        Thread.sleep(1000);
      }
 catch (      InterruptedException e) {
        throw new FlywayException("Waiting for Flashback cleanup interrupted",e);
      }
    }
  }
  if (oracle18orNewer) {
    while (database.queryReturnsRows("SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER = ?\n" + " AND TABLE_NAME LIKE 'SYS_FBA_DDL_COLMAP_%'",name)) {
      try {
        LOG.debug("Actively waiting for Flashback colmap cleanup");
        Thread.sleep(1000);
      }
 catch (      InterruptedException e) {
        throw new FlywayException("Waiting for Flashback colmap cleanup interrupted",e);
      }
    }
  }
}
