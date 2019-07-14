@Override protected MigrationVersion determineVersion(){
  try {
    int buildId=getMainConnection().getJdbcTemplate().queryForInt("SELECT VALUE FROM INFORMATION_SCHEMA.SETTINGS WHERE NAME = 'info.BUILD_ID'");
    return MigrationVersion.fromVersion(super.determineVersion().getVersion() + "." + buildId);
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Unable to determine H2 build ID",e);
  }
}
