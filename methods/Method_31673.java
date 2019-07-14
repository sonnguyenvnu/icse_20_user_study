@Override protected void doAddAppliedMigration(int installedRank,MigrationVersion version,String description,MigrationType type,String script,Integer checksum,int executionTime,boolean success){
  connection.restoreOriginalState();
  if (!database.supportsDdlTransactions()) {
    table.lock();
  }
  try {
    String versionStr=version == null ? null : version.toString();
    jdbcTemplate.update(database.getInsertStatement(table),installedRank,versionStr,description,type.name(),script,checksum,database.getInstalledBy(),executionTime,success);
    LOG.debug("Schema History table " + table + " successfully updated to reflect changes");
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Unable to insert row for version '" + version + "' in Schema History table " + table,e);
  }
}
