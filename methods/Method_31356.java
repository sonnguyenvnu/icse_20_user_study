@Override protected void doClean() throws SQLException {
  List<String> procedures=jdbcTemplate.queryForStringList("SELECT t.procname FROM \"informix\".sysprocedures AS t" + " WHERE t.owner=? AND t.mode='O' AND t.externalname IS NULL" + " AND t.procname NOT IN (" + " 'tscontainerusage', 'tscontainertotalused', 'tscontainertotalpages'," + " 'tscontainernelems', 'tscontainerpctused', 'tsl_flushstatus', 'tsmakenullstamp'" + ")",name);
  for (  String procedure : procedures) {
    jdbcTemplate.execute("DROP PROCEDURE " + procedure);
  }
  for (  Table table : allTables()) {
    table.drop();
  }
  List<String> sequences=jdbcTemplate.queryForStringList("SELECT t.tabname FROM \"informix\".systables AS t" + " WHERE owner=? AND t.tabid > 99 AND t.tabtype='Q'" + " AND t.tabname NOT IN ('iot_data_seq')",name);
  for (  String sequence : sequences) {
    jdbcTemplate.execute("DROP SEQUENCE " + sequence);
  }
}
