/** 
 * ??column??????.
 */
public List<T> listByColumn(Map<String,Object> paramMap){
  if (paramMap == null) {
    return null;
  }
  return sessionTemplate.selectList(getStatement(SQL_LIST_BY_COLUMN),paramMap);
}
