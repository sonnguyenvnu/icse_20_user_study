@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForBoolean("SELECT EXISTS (\n" + "  SELECT 1\n" + "  FROM   pg_catalog.pg_class c\n" + "  JOIN   pg_catalog.pg_namespace n ON n.oid = c.relnamespace\n" + "  WHERE  n.nspname = ?\n" + "  AND    c.relname = ?\n" + "  AND    c.relkind = 'r'\n" + ")",schema.getName(),name.toLowerCase());
}
