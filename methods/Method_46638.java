@Override public Serializable execute(TransactionCmd transactionCmd) throws TxClientException {
  try {
    GetAspectLogParams getAspectLogParams=transactionCmd.getMsg().loadBean(GetAspectLogParams.class);
    AspectLog txLog=txLogHelper.getTxLog(getAspectLogParams.getGroupId(),getAspectLogParams.getUnitId());
    if (Objects.isNull(txLog)) {
      throw new TxClientException("non exists aspect log.");
    }
    TransactionInfo transactionInfo=SerializerContext.getInstance().deSerialize(txLog.getBytes(),TransactionInfo.class);
    return transactionInfo.toJsonObject();
  }
 catch (  SerializerException e) {
    throw new TxClientException(e);
  }
}
