public static FundingRecord adaptTransaction(IndependentReserveTransaction transaction){
  BigDecimal amount=null;
  if (transaction.getDebit() != null) {
    amount=transaction.getDebit();
  }
 else   if (transaction.getCredit() != null) {
    amount=transaction.getCredit();
  }
  return new FundingRecord(null,transaction.getCreatedTimestamp(),new Currency(transaction.getCurrencyCode()),amount,null,transaction.getBitcoinTransactionId(),adaptTransactionTypeToFundingRecordType(transaction.getType()),adaptTransactionStatusToFundingRecordStatus(transaction.getStatus()),transaction.getBalance(),null,transaction.getComment());
}
