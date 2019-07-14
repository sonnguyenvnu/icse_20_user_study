public void logTransaction(TransactionInfo info){
  long transactionMillis=info.getEndTimeMillis() - info.getStartTimeMillis();
  if (transactionThresholdMillis > 0 && transactionMillis > transactionThresholdMillis) {
    StringBuilder buf=new StringBuilder();
    buf.append("long time transaction, take ");
    buf.append(transactionMillis);
    buf.append(" ms : ");
    for (    String sql : info.getSqlList()) {
      buf.append(sql);
      buf.append(";");
    }
    LOG.error(buf.toString(),new TransactionTimeoutException());
  }
}
