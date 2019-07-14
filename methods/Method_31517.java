@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForBoolean("SELECT CAST(" + "CASE WHEN EXISTS(" + "  SELECT 1 FROM [" + databaseName + "].INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=? AND TABLE_NAME=?" + ") " + "THEN 1 ELSE 0 " + "END " + "AS BIT)",schema.getName(),name);
}
