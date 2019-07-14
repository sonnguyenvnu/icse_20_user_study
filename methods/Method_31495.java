@Override public <T>T lock(Table table,Callable<T> callable){
  return new SQLServerApplicationLockTemplate(this,jdbcTemplate,originalDatabaseName,table.toString().hashCode()).execute(callable);
}
