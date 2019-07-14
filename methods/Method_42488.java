/** 
 * ??id????.
 */
public T getById(Long id){
  return sessionTemplate.selectOne(getStatement(SQL_SELECT_BY_ID),id);
}
