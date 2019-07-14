@Override public List<SqlExecuteResult> execute(String transactionId,SqlExecuteRequest request) throws Exception {
  TransactionExecutor executor=transactionExecutorMap.get(transactionId);
  if (executor != null) {
    TransactionInfo info=transactionInfoMap.get(transactionId);
    if (null != info) {
      info.setLastExecuteTime(new Date());
      info.getSqlHistory().addAll(request.getSql());
    }
    return executor.execute(request);
  }
  return null;
}
