/** 
 * ????
 * @param query
 * @return
 * @throws MyException
 */
public List<Project> query(ProjectQuery query) throws MyException {
  Assert.notNull(query);
  Page page=new Page(query);
  ProjectCriteria example=getProjectCriteria(query);
  example.setLimitStart(page.getStart());
  example.setMaxResults(page.getSize());
  example.setOrderByClause(query.getSort() == null ? TableField.SORT.SEQUENCE_DESC : query.getSort());
  return projectDao.selectByExample(example);
}
