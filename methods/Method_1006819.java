public String match(String sql,long start,long rows){
  if (rows > 0)   return ORACLE_PAGINATION.replace(ORACLE_PAGINATION_REGX_END,String.valueOf(start + rows)).replace(ORACLE_PAGINATION_REGX_BEGIN,String.valueOf(start)).replace(ORACLE_PAGINATION_REGX_SQL,sql);
  return sql;
}
