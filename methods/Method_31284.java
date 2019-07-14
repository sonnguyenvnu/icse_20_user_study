@Override protected boolean doEmpty() throws SQLException {
  return jdbcTemplate.queryForInt("select count(*) from (" + "select 1 from syscat.tables where tabschema = ? " + "union " + "select 1 from syscat.views where viewschema = ? " + "union " + "select 1 from syscat.sequences where seqschema = ? " + "union " + "select 1 from syscat.indexes where indschema = ? " + "union " + "select 1 from syscat.routines where ROUTINESCHEMA = ? " + "union " + "select 1 from syscat.triggers where trigschema = ? " + ")",name,name,name,name,name,name) == 0;
}
