/** 
 * @return The major and minor version of the database.
 */
protected MigrationVersion determineVersion(){
  try {
    return MigrationVersion.fromVersion(jdbcMetaData.getDatabaseMajorVersion() + "." + jdbcMetaData.getDatabaseMinorVersion());
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Unable to determine the major version of the database",e);
  }
}
