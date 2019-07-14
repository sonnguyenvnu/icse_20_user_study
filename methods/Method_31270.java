@Override protected boolean doExists() throws SQLException {
  if (schema.cockroachDB1) {
    return jdbcTemplate.queryForBoolean("SELECT EXISTS (\n" + "   SELECT 1\n" + "   FROM   information_schema.tables \n" + "   WHERE  table_schema = ?\n" + "   AND    table_name = ?\n" + ")",schema.getName(),name);
  }
  return jdbcTemplate.queryForBoolean("SELECT EXISTS (\n" + "   SELECT 1\n" + "   FROM   information_schema.tables \n" + "   WHERE  table_catalog = ?\n" + "   AND    table_schema = 'public'\n" + "   AND    table_name = ?\n" + ")",schema.getName(),name);
}
