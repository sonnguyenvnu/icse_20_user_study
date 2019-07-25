public boolean insert(PO po) throws Exception {
  Assert.notNull(po);
  if (MyString.isEmpty(po.getId())) {
    po.setId(IdGenerator.getId(tableId));
  }
  if (po.getSequence() == null) {
    po.setSequence(System.currentTimeMillis());
  }
  if (po.getSequence() < 0) {
    po.setSequence(System.currentTimeMillis());
  }
  if (po.getSequence() > IConst.C_MAX_SEQUENCE) {
    po.setSequence(IConst.C_MAX_SEQUENCE);
  }
  po.setCreateTime(new Date());
  return newBaseDao.insert(po) > 0;
}
