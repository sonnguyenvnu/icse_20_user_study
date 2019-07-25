/** 
 * ????
 * @param query
 * @return
 * @throws MyException
 */
public List<User> query(UserQuery query) throws MyException {
  Assert.notNull(query);
  Page page=new Page(query);
  UserCriteria example=getUserCriteria(query);
  example.setLimitStart(page.getStart());
  example.setMaxResults(page.getSize());
  example.setOrderByClause(query.getSort() == null ? TableField.SORT.CREATE_TIME_DES : query.getSort());
  return userDao.selectByExample(example);
}
