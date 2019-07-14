static boolean isRunningInPerconaXtraDBClusterWithStrictMode(JdbcTemplate jdbcTemplate){
  try {
    if ("ENFORCING".equals(jdbcTemplate.queryForString("select VARIABLE_VALUE from performance_schema.global_variables" + " where variable_name = 'pxc_strict_mode'"))) {
      LOG.debug("Detected Percona XtraDB Cluster in strict mode");
      return true;
    }
  }
 catch (  SQLException e) {
    LOG.debug("Unable to detect whether we are running in a Percona XtraDB Cluster. Assuming not to be.");
  }
  return false;
}
