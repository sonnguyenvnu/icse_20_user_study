@Override protected boolean doEmpty() throws SQLException {
  if (cockroachDB1) {
    return !jdbcTemplate.queryForBoolean("SELECT EXISTS (" + "  SELECT 1" + "  FROM information_schema.tables" + "  WHERE table_schema=?" + "  AND table_type='BASE TABLE'" + ")",name);
  }
  return !jdbcTemplate.queryForBoolean("SELECT EXISTS (" + "  SELECT 1" + "  FROM information_schema.tables " + "  WHERE table_catalog=?" + "  AND table_schema='public'" + "  AND table_type='BASE TABLE'" + " UNION ALL" + "  SELECT 1" + "  FROM information_schema.sequences " + "  WHERE sequence_catalog=?" + "  AND sequence_schema='public'" + ")",name,name);
}
