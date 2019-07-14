/** 
 * ??column?????.
 */
public Long getCountByColumn(Map<String,Object> paramMap){
  if (paramMap == null) {
    return null;
  }
  return sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_COLUMN),paramMap);
}
