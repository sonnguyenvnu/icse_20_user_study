/** 
 * ????
 * @param query
 * @return
 * @throws MyException
 */
public List<Module> query(ModuleQuery query) throws MyException {
  Assert.notNull(query);
  Page page=new Page(query);
  ModuleCriteria example=getModuleCriteria(query);
  if (page.getSize() != ALL_PAGE_SIZE) {
    example.setLimitStart(page.getStart());
    example.setMaxResults(page.getSize());
  }
  example.setOrderByClause(query.getSort() == null ? TableField.SORT.SEQUENCE_DESC : query.getSort());
  return moduleDao.selectByExample(example);
}
