private boolean tryLock() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT GET_LOCK(?,10)",lockName) == 1;
}
