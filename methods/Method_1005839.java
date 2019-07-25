/** 
 * ?????
 * @param query
 * @return
 * @throws MyException
 */
public List<Error> query(ErrorQuery query) throws MyException {
  Assert.notNull(query);
  Page page=new Page(query);
  ErrorCriteria example=getErrorCriteria(query);
  example.setLimitStart(page.getStart());
  example.setMaxResults(page.getSize());
  example.setOrderByClause(query.getSort() == null ? TableField.SORT.SEQUENCE_DESC : query.getSort());
  return errorDao.selectByExample(example);
}
