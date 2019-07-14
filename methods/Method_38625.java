/** 
 * Builds page sql using LIMIT keyword after the SELECT.
 */
@Override protected String buildPageSql(String sql,final int from,final int pageSize){
  sql=removeSelect(sql);
  return "select LIMIT " + from + ' ' + pageSize + sql;
}
