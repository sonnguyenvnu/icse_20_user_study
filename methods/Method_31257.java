@Override protected MigrationVersion determineVersion(){
  String version;
  try {
    version=getMainConnection().getJdbcTemplate().queryForString("SELECT value FROM crdb_internal.node_build_info where field='Version'");
    if (version == null) {
      version=getMainConnection().getJdbcTemplate().queryForString("SELECT value FROM crdb_internal.node_build_info where field='Tag'");
    }
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Unable to determine CockroachDB version",e);
  }
  int firstDot=version.indexOf(".");
  int majorVersion=Integer.parseInt(version.substring(1,firstDot));
  String minorPatch=version.substring(firstDot + 1);
  int minorVersion=Integer.parseInt(minorPatch.substring(0,minorPatch.indexOf(".")));
  return MigrationVersion.fromVersion(majorVersion + "." + minorVersion);
}
