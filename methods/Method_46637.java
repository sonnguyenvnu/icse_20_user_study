@Override public Serializable execute(TransactionCmd transactionCmd) throws TxClientException {
  aspectLogHelper.delete(transactionCmd.getGroupId());
  return null;
}
