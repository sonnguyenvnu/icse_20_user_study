/** 
 * Builds page SQL using <code>limit</code> keyword. Uses SQL_CALC_FOUND_ROWS for  {@link #buildCountSql(String)}.
 */
@Override protected String buildPageSql(String sql,final int from,final int pageSize){
  sql=removeSelect(sql);
  return "select SQL_CALC_FOUND_ROWS " + sql + " limit " + from + ", " + pageSize;
}
