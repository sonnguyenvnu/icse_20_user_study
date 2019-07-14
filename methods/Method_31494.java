@Override public Schema getSchema(String name){
  return new SQLServerSchema(jdbcTemplate,database,originalDatabaseName,name);
}
