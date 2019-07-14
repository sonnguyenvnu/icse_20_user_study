/** 
 * Removes the first 'select' from the sql query.
 */
protected String removeSelect(String sql){
  int ndx=StringUtil.indexOfIgnoreCase(sql,"select");
  if (ndx != -1) {
    sql=sql.substring(ndx + 6);
  }
  return sql;
}
