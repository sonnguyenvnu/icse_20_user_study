private boolean hasUserVariableResetCapability(){
  try {
    jdbcTemplate.queryForStringList(userVariablesQuery);
    return true;
  }
 catch (  SQLException e) {
    LOG.debug("Disabled user variable reset as " + (database.isMariaDB() ? USER_VARIABLES_TABLE_MARIADB : USER_VARIABLES_TABLE_MYSQL) + "cannot be queried (SQL State: " + e.getSQLState() + ", Error Code: " + e.getErrorCode() + ")");
    return false;
  }
}
