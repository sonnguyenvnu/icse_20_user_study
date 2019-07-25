public List<PO> select(Query query) throws MyException {
  Assert.notNull(query,"query can't be null");
  if (query.getSort() == null) {
    query.setSort(TableField.SORT.SEQUENCE_DESC);
  }
  return newBaseDao.select(query);
}
