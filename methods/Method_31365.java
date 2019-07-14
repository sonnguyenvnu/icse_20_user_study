private static boolean isEventSchedulerQueryable(JdbcTemplate jdbcTemplate){
  try {
    jdbcTemplate.queryForString("SELECT event_name FROM information_schema.events LIMIT 1");
    return true;
  }
 catch (  SQLException e) {
    LOG.debug("Detected unqueryable MariaDB event scheduler, most likely due to it being OFF or DISABLED.");
    return false;
  }
}
