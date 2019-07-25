@Override public void truncate(String tableName){
  if (!exists(tableName))   return;
  Sql sql=Sqls.createf("TRUNCATE TABLE %s",tableName);
  _exec(sql);
  return;
}
