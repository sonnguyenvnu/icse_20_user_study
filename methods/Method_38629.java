/** 
 * Removes the first part of the sql up to the relevant 'from'. Tries to detect sub-queries in the 'select' part.
 */
protected String removeToFrom(String sql){
  int from=0;
  int fromCount=1;
  int selectCount=0;
  int lastNdx=0;
  while (true) {
    int ndx=StringUtil.indexOfIgnoreCase(sql,"from",from);
    if (ndx == -1) {
      break;
    }
    String left=sql.substring(lastNdx,ndx);
    selectCount+=StringUtil.countIgnoreCase(left,"select");
    if (fromCount >= selectCount) {
      sql=sql.substring(ndx);
      break;
    }
    lastNdx=ndx;
    from=ndx + 4;
    fromCount++;
  }
  return sql;
}
