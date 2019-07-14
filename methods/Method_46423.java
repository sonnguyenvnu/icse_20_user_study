@Override public void saveLog(TxLog txLog){
  if (needAware && Objects.isNull(txLcnLogDbHelper)) {
    txLcnLogDbHelper=SpringUtils.getBean(TxLcnLogDbHelper.class);
    needAware=false;
  }
  if (Objects.nonNull(txLcnLogDbHelper)) {
    txLcnLogDbHelper.insert(txLog);
    return;
  }
  log.warn("tx-logger db configure fail.");
}
