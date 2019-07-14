/** 
 * ??column????.
 */
public T getByColumn(Map<String,Object> paramMap){
  if (paramMap == null) {
    return null;
  }
  return sessionTemplate.selectOne(getStatement(SQL_LIST_BY_COLUMN),paramMap);
}
