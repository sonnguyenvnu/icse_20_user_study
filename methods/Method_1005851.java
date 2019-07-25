/** 
 * ????
 * @param query
 * @return
 * @throws MyException
 */
public List<Source> query(SourceQuery query) throws MyException {
  Assert.notNull(query);
  Page page=new Page(query);
  SourceCriteria example=getSourceCriteria(query);
  example.setLimitStart(page.getStart());
  example.setMaxResults(page.getSize());
  example.setOrderByClause(query.getSort() == null ? TableField.SORT.SEQUENCE_DESC : query.getSort());
  return sourceDao.selectByExample(example);
}
