/** 
 * ??SQL
 */
protected static String concat(Parsed parsed,String sql,Map<String,Object> queryMap){
  StringBuilder sb=new StringBuilder();
  boolean flag=(sql.contains(SqlScript.WHERE) || sql.contains(SqlScript.WHERE.toLowerCase()));
  for (  String key : queryMap.keySet()) {
    String mapper=parsed.getMapper(key);
    if (flag) {
      sb.append(Conjunction.AND.sql()).append(mapper).append(SqlScript.EQ_PLACE_HOLDER);
    }
 else {
      sb.append(SqlScript.WHERE).append(mapper).append(SqlScript.EQ_PLACE_HOLDER);
      flag=true;
    }
  }
  sql+=sb.toString();
  return sql;
}
