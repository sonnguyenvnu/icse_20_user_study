@Override protected boolean doEmpty() throws SQLException {
  List<String> params=new ArrayList<>(Arrays.asList(name,name,name,name,name));
  if (database.eventSchedulerQueryable) {
    params.add(name);
  }
  return jdbcTemplate.queryForInt("SELECT SUM(found) FROM (" + "(SELECT 1 as found FROM information_schema.tables WHERE table_schema=?) UNION ALL " + "(SELECT 1 as found FROM information_schema.views WHERE table_schema=? LIMIT 1) UNION ALL " + "(SELECT 1 as found FROM information_schema.table_constraints WHERE table_schema=? LIMIT 1) UNION ALL " + "(SELECT 1 as found FROM information_schema.triggers WHERE trigger_schema=? LIMIT 1) UNION ALL " + "(SELECT 1 as found FROM information_schema.routines WHERE routine_schema=? LIMIT 1)" + (database.eventSchedulerQueryable ? " UNION ALL (SELECT 1 as found FROM information_schema.events WHERE event_schema=? LIMIT 1)" : "") + ") as all_found",params.toArray(new String[0])) == 0;
}
