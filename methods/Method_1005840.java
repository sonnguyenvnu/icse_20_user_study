public List<Interface> query(InterfaceQuery query) throws MyException {
  Assert.notNull(query);
  Assert.isTrue(query.getProjectId() != null || query.getModuleId() != null,"projectId?moduleId??????");
  Page page=new Page(query);
  InterfaceCriteria example=getInterfaceCriteria(query);
  if (page.getSize() != ALL_PAGE_SIZE) {
    example.setLimitStart(page.getStart());
    example.setMaxResults(page.getSize());
  }
  example.setOrderByClause(query.getSort() == null ? TableField.SORT.SEQUENCE_DESC : query.getSort());
  return interfaceDao.selectByExample(example);
}
