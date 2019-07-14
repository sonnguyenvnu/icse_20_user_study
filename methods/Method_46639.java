@Override public Serializable execute(TransactionCmd transactionCmd) throws TxClientException {
  log.info("transactionCmd->{}",transactionCmd);
  NotifyConnectParams notifyConnectParams=transactionCmd.getMsg().loadBean(NotifyConnectParams.class);
  rpcClientInitializer.connect(new InetSocketAddress(notifyConnectParams.getHost(),notifyConnectParams.getPort()));
  return null;
}
