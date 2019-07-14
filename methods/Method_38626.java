/** 
 * Builds count sql using COUNT(*).
 */
@Override protected String buildCountSql(String sql){
  sql=removeToFrom(sql);
  sql=removeLastOrderBy(sql);
  return "select count(*) " + sql;
}
