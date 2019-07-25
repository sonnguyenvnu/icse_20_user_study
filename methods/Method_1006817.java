public String match(String sql,long start,long rows){
  if (rows == 0)   return sql;
  StringBuilder sb=new StringBuilder();
  sb.append(sql);
  sb.append(SqlScript.LIMIT).append(start).append(",").append(rows);
  return sb.toString();
}
