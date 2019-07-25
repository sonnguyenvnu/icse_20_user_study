@Override public void create(Transaction transaction) throws ServiceException {
  String transactionDetails=transaction.toJSONString();
  if (!StringUtils.isBlank(transactionDetails)) {
    transaction.setDetails(transactionDetails);
  }
  super.create(transaction);
}
