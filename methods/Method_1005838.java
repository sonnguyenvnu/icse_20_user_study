/** 
 * ????
 * @param query
 * @return
 * @throws MyException
 */
public List<Debug> query(DebugQuery query) throws MyException {
  Assert.notNull(query);
  Page page=new Page(query);
  DebugCriteria example=getDebugCriteria(query);
  example.setLimitStart(page.getStart());
  example.setMaxResults(page.getSize());
  example.setOrderByClause(query.getSort() == null ? TableField.SORT.SEQUENCE_DESC : query.getSort());
  return debugDao.selectByExample(example);
}
