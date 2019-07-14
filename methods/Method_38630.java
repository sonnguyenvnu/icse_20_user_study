/** 
 * Removes everything from last "order by".
 */
protected String removeLastOrderBy(String sql){
  int ndx=StringUtil.lastIndexOfIgnoreCase(sql,"order by");
  if (ndx != -1) {
    int ndx2=sql.lastIndexOf(sql,')');
    if (ndx > ndx2) {
      sql=sql.substring(0,ndx);
    }
  }
  return sql;
}
