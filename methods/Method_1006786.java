public static String mapper(String sql,Parsed parsed){
  if (parsed.isNoSpec())   return sql;
  sql=mapperName(sql,parsed);
  boolean flag=sql.contains(SQL_KEYWORD_MARK);
  for (  String property : parsed.getPropertyMapperMap().keySet()) {
    if (flag) {
      String key=SQL_KEYWORD_MARK + property + SQL_KEYWORD_MARK;
      if (sql.contains(key)) {
        String value=parsed.getMapper(property);
        if (!value.startsWith(SQL_KEYWORD_MARK)) {
          value=SQL_KEYWORD_MARK + parsed.getMapper(property) + SQL_KEYWORD_MARK;
        }
        sql=sql.replace(key,value);
        continue;
      }
    }
    String key=SPACE + property + SPACE;
    String value=SPACE + parsed.getMapper(property) + SPACE;
    sql=sql.replaceAll(key,value);
  }
  return sql;
}
