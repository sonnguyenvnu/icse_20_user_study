/** 
 * ??id????.
 */
public T getById(String id){
  return sessionTemplate.selectOne(getStatement(SQL_SELECT_BY_ID),id);
}
