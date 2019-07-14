@Override protected boolean doEmpty() throws SQLException {
  return !jdbcTemplate.queryForBoolean("SELECT EXISTS (   SELECT 1\n" + "   FROM   pg_catalog.pg_class c\n" + "   JOIN   pg_catalog.pg_namespace n ON n.oid = c.relnamespace\n" + "   WHERE  n.nspname = ?)",name);
}
