@Override public int update(final HmilyTransaction hmilyTransaction) throws HmilyRuntimeException {
  hmilyTransaction.setLastTime(new Date());
  hmilyTransaction.setVersion(hmilyTransaction.getVersion() + 1);
  hmilyTransaction.setRetriedCount(hmilyTransaction.getRetriedCount() + 1);
  try {
    writeFile(hmilyTransaction);
  }
 catch (  Exception e) {
    throw new HmilyRuntimeException("update data exception!");
  }
  return 1;
}
