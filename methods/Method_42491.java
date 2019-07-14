/** 
 * ??????????.
 */
public List<T> listBy(Map<String,Object> paramMap){
  if (paramMap == null) {
    return null;
  }
  return sessionTemplate.selectList(getStatement(SQL_LIST_BY),paramMap);
}
