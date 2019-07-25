/** 
 * ????
 * @param query
 * @return
 * @throws MyException
 */
public List<Setting> query(SettingQuery query) throws MyException {
  Assert.notNull(query);
  Page page=new Page(query);
  SettingCriteria example=getSettingCriteria(query);
  example.setLimitStart(page.getStart());
  example.setMaxResults(page.getSize());
  example.setOrderByClause(query.getSort() == null ? TableField.SORT.SEQUENCE_DESC : query.getSort());
  return settingDao.selectByExample(example);
}
