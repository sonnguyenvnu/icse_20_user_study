/** 
 * ?????? getBy: selectOne <br/>
 * @param paramMap
 * @return
 */
public T getBy(Map<String,Object> paramMap){
  if (paramMap == null) {
    return null;
  }
  return sessionTemplate.selectOne(getStatement(SQL_LIST_BY),paramMap);
}
