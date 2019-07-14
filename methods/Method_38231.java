/** 
 * Pre-process SQL before using it. If string starts with a non-ascii char or it has no spaces, it will be loaded from the query map.
 */
protected String preprocessSql(String sqlString){
  if (sqlString.charAt(0) == '{') {
    return sqlString;
  }
  if (!CharUtil.isAlpha(sqlString.charAt(0))) {
    sqlString=sqlString.substring(1);
  }
 else   if (sqlString.indexOf(' ') != -1) {
    return sqlString;
  }
  final String sqlFromMap=dbOom.queryMap().getQuery(sqlString);
  if (sqlFromMap != null) {
    sqlString=sqlFromMap.trim();
  }
  return sqlString;
}
